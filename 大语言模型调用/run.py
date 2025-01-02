import pymysql
from rocketmq.client import Producer, PushConsumer, Message, ConsumeStatus
import time

from api import send_data
from config import rocketmq_host, db_config


# 数据库初始化
def init_db_connection():
    return pymysql.connect(
        host=db_config["host"],
        port=db_config["port"],
        user=db_config["user"],
        password=db_config["password"],
        database=db_config["database"],
        charset="utf8mb4"
    )


# 连接并发送消息 (Producer)
def send_message():
    producer = Producer("testProducerGroup")
    producer.set_name_server_address(rocketmq_host)  # 替换为你的 NameServer 地址
    producer.start()
    try:
        msg = Message("testTopic")
        msg.set_keys("key1")
        msg.set_tags("tag1")
        msg.set_body("Hello, RocketMQ!")
        result = producer.send_sync(msg)
        print(f"Message sent: {result.status}")
    finally:
        producer.shutdown()


# 连接并接收消息 (Consumer)
def consume_message():
    consumer = PushConsumer("testConsumerGroup")
    consumer.set_name_server_address(rocketmq_host)  # 替换为你的 NameServer 地址

    def callback(msg):
        message = msg.body.decode('gbk')
        message = message.split("&&&&")
        id = message[0]
        content = message[1]
        tag = send_data(content)
        # 将结果存储到数据库
        try:
            connection = init_db_connection()
            with connection.cursor() as cursor:
                sql = """
                update advertisement set ad_feature = %s where ad_id = %s
                """
                cursor.execute(sql, (tag, id))
                connection.commit()
            print(f"Message processed and stored in DB: {id}")
        except Exception as e:
            print(f"Failed to store message in DB: {e}")
            return ConsumeStatus.RECONSUME_LATER  # 消息处理失败，稍后重试
        finally:
            connection.close()

        return ConsumeStatus.CONSUME_SUCCESS

    consumer.subscribe("testTopic", callback)

    # 启动消费
    consumer.start()

    try:
        while True:
            # 消费消息
            time.sleep(1)
    except KeyboardInterrupt:
        consumer.shutdown()


if __name__ == "__main__":
    # 示例调用
    # send_message()
    consume_message()  # 启用消费时调用
