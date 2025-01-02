import joblib
import pandas as pd
import logging

# 配置日志
def setup_logger():
    # 创建日志格式
    formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")

    # 创建日志处理器（文件）
    file_handler = logging.FileHandler('log.txt')
    file_handler.setFormatter(formatter)

    # 创建日志处理器（控制台）
    console_handler = logging.StreamHandler()
    console_handler.setFormatter(formatter)

    # 配置根日志记录器
    logger = logging.getLogger()
    logger.setLevel(logging.INFO)
    logger.addHandler(file_handler)
    logger.addHandler(console_handler)

    return logger

# 设置日志记录器
logger = setup_logger()

# 指定要加载的模型版本（文件夹编号）
version = 2  # 例如，加载编号为 2 的模型

# 加载模型和编码器
model_path = f"models/{version}/model.pkl"
encoder_path = f"models/{version}/encoder.pkl"
valid_columns_path = f"models/{version}/valid_columns.pkl"

try:
    model = joblib.load(model_path)
    encoder = joblib.load(encoder_path)
    valid_columns = joblib.load(valid_columns_path)
    logger.info(f"成功加载模型和编码器，版本: {version}")
except Exception as e:
    logger.error(f"加载模型或编码器失败: {e}")
    raise

# 预测函数
def predict_preferences(user_info):
    try:
        # 将用户信息转换为 DataFrame
        user_df = pd.DataFrame([user_info])

        # 对分类变量进行编码
        encoded_user = encoder.transform(user_df[encoder.feature_names_in_])
        encoded_user_df = pd.DataFrame(encoded_user, columns=encoder.get_feature_names_out())

        # 拼接年龄特征
        user_X = pd.concat([user_df[['age']], encoded_user_df], axis=1)

        # 预测
        preferences = model.predict(user_X)  # 返回一个数组，每个元素是一个类别的预测值

        # 将预测结果转换为字典
        result = {label: float(prob) for label, prob in zip(valid_columns, preferences[0])}
        logger.info(f"预测成功: {result}")
        return result
    except Exception as e:
        logger.error(f"预测失败: {e}")
        raise

# 示例调用
if __name__ == "__main__":
    user_info = {
        "age": 25,
        "gender": "男",
        "occupation": "白领",
        "education_level": "高中",
        "region": "上海",
        "country": "中国",
        "device": "智能手机"
    }
    try:
        preferences = predict_preferences(user_info)
        print("用户偏好比例:", preferences)
    except Exception as e:
        logger.error(f"程序运行失败: {e}")