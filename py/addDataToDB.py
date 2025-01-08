import random
import pandas as pd
from sqlalchemy import create_engine
import logging
import string

# 配置日志
logging.basicConfig(
    level=logging.INFO,  # 设置日志级别为 INFO
    format='%(asctime)s - %(levelname)s - %(message)s',  # 设置日志格式
    handlers=[
        logging.FileHandler('log.txt'),  # 将日志保存到 log.txt 文件
        logging.StreamHandler()  # 将日志输出到控制台
    ]
)

# 数据库连接
engine = create_engine("mysql+pymysql://root:123456@localhost:3306/adv")

# 定义枚举值
genders = ['男', '女', '不愿透露']
occupations = ['学生', '老师', '白领', '蓝领', '研究工作者', '工程师', '服务工作者', '不愿透露']
education_levels = ['初中', '高中', '大学', '不愿透露']
regions = ['上海', '北京', '广州', '深圳', '武汉', '成都', '西安', '杭州', '南京', '重庆', '天津', '苏州', '长沙', '青岛', '大连', '厦门', '宁波', '沈阳', '昆明', '无锡']
countries = ['中国']
devices = ['台式设备', '笔记本电脑', '平板电脑', '智能手机', '其他设备']

# 生成随机 user_name
def generate_user_name(existing_names):
    while True:
        name = f"user_{random.randint(10000, 99999)}"
        if name not in existing_names:
            return name

# 生成随机 finger_print
def generate_finger_print():
    # 生成一个随机的 16 位字符串作为 finger_print
    return ''.join(random.choices(string.ascii_letters + string.digits, k=16))

# 生成随机 preference
def generate_preference(age, gender):
    preference = {
        'Fashion': random.randint(0, 100),
        'Art': random.randint(0, 100),
        'Entertainment': random.randint(0, 100),
        'Education': random.randint(0, 100),
        'Pets': random.randint(0, 100),
        'Eco': random.randint(0, 100),
        'Weather': random.randint(0, 100),
        'Technology': random.randint(0, 100),
        'Politics': random.randint(0, 100),
        'Economy': random.randint(0, 100)
    }

    # 根据 age 调整 preference
    if age <= 25:
        top_values = ['Fashion', 'Entertainment']
    elif 25 < age <= 40:
        top_values = ['Politics', 'Economy', 'Education']
    else:
        top_values = random.choice(list(preference.keys()))  # 随机选择

    # 确保 top_values 中的值最大或第二大
    for key in top_values:
        preference[key] = random.randint(80, 100)  # 设置为较大值

    # 根据 gender 调整 preference
    if gender == '女':
        for key in ['Fashion', 'Art', 'Pets']:
            preference[key] = random.randint(60, 100)  # 设置为偏大值
    elif gender == '男':
        for key in ['Technology', 'Politics', 'Economy']:
            preference[key] = random.randint(60, 100)  # 设置为偏大值

    return preference

# 生成 n 条数据
def generate_data(n):
    data = []
    existing_names = set()

    for _ in range(n):
        age = random.randint(0, 70)
        gender = random.choice(genders)
        user_name = generate_user_name(existing_names)
        existing_names.add(user_name)

        preference = generate_preference(age, gender)

        row = {
            'user_name': user_name,
            'age': age,
            'gender': gender,
            'occupation': random.choice(occupations),
            'education_level': random.choice(education_levels),
            'region': random.choice(regions),
            'country': random.choice(countries),
            'device': random.choice(devices),
            'finger_print': generate_finger_print(),  # 添加 finger_print
            **preference
        }
        data.append(row)

    # 确保 DataFrame 只包含数据库表中存在的列
    columns = [
        'finger_print','user_name', 'age', 'gender', 'occupation', 'education_level', 'region', 'country', 'device',
        'Fashion', 'Art', 'Entertainment', 'Education', 'Pets', 'Eco', 'Weather', 'Technology', 'Politics', 'Economy'
    ]
    return pd.DataFrame(data, columns=columns)

# 保存数据到数据库
def save_to_database(df):
    df.to_sql('user_train_data', con=engine, if_exists='append', index=False)
    logging.info(f"成功保存 {len(df)} 条数据到数据库")

# 主函数
if __name__ == "__main__":
    n = 100
    df = generate_data(n)
    logging.info("生成的数据：")
    logging.info(df)
    save_to_database(df)

# import random

# import pandas as pd
# from sqlalchemy import create_engine
# import logging
#
# # 配置日志
# logging.basicConfig(
#     level=logging.INFO,  # 设置日志级别为 INFO
#     format='%(asctime)s - %(levelname)s - %(message)s',  # 设置日志格式
#     handlers=[
#         logging.FileHandler('log.txt'),  # 将日志保存到 log.txt 文件
#         logging.StreamHandler()  # 将日志输出到控制台
#     ]
# )
#
# # 数据库连接
# engine = create_engine("mysql+pymysql://root:123456@localhost:3306/adv")
#
# # 定义枚举值
# genders = ['男', '女', '不愿透露']
# occupations = ['学生', '老师', '白领', '蓝领', '研究工作者', '工程师', '服务工作者', '不愿透露']
# education_levels = ['初中', '高中', '大学', '不愿透露']
# regions = ['上海', '北京', '广州', '深圳', '武汉', '成都', '西安', '杭州', '南京', '重庆', '天津', '苏州', '长沙', '青岛', '大连', '厦门', '宁波', '沈阳', '昆明', '无锡']
# countries = ['中国']
# devices = ['台式设备', '笔记本电脑', '平板电脑', '智能手机', '其他设备']
#
# # 生成随机 user_name
# def generate_user_name(existing_names):
#     while True:
#         name = f"user_{random.randint(10000, 99999)}"
#         if name not in existing_names:
#             return name
#
# # 生成随机 preference
# def generate_preference(age, gender):
#     preference = {
#         'Fashion': random.randint(0, 100),
#         'Art': random.randint(0, 100),
#         'Entertainment': random.randint(0, 100),
#         'Education': random.randint(0, 100),
#         'Pets': random.randint(0, 100),
#         'Eco': random.randint(0, 100),
#         'Weather': random.randint(0, 100),
#         'Technology': random.randint(0, 100),
#         'Politics': random.randint(0, 100),
#         'Economy': random.randint(0, 100)
#     }
#
#     # 根据 age 调整 preference
#     if age <= 25:
#         top_values = ['Fashion', 'Entertainment']
#     elif 25 < age <= 40:
#         top_values = ['Politics', 'Economy', 'Education']
#     else:
#         top_values = random.choice(list(preference.keys()))  # 随机选择
#
#     # 确保 top_values 中的值最大或第二大
#     for key in top_values:
#         preference[key] = random.randint(80, 100)  # 设置为较大值
#
#     # 根据 gender 调整 preference
#     if gender == '女':
#         for key in ['Fashion', 'Art', 'Pets']:
#             preference[key] = random.randint(60, 100)  # 设置为偏大值
#     elif gender == '男':
#         for key in ['Technology', 'Politics', 'Economy']:
#             preference[key] = random.randint(60, 100)  # 设置为偏大值
#
#     return preference
#
# # 生成 n 条数据
# def generate_data(n):
#     data = []
#     existing_names = set()
#
#     for _ in range(n):
#         age = random.randint(0, 70)
#         gender = random.choice(genders)
#         user_name = generate_user_name(existing_names)
#         existing_names.add(user_name)
#
#         preference = generate_preference(age, gender)
#
#         row = {
#             'user_name': user_name,
#             'age': age,
#             'gender': gender,
#             'occupation': random.choice(occupations),
#             'education_level': random.choice(education_levels),
#             'region': random.choice(regions),
#             'country': random.choice(countries),
#             'device': random.choice(devices),
#             **preference
#         }
#         data.append(row)
#
#     # 确保 DataFrame 只包含数据库表中存在的列
#     columns = [
#         'user_name', 'age', 'gender', 'occupation', 'education_level', 'region', 'country', 'device',
#         'Fashion', 'Art', 'Entertainment', 'Education', 'Pets', 'Eco', 'Weather', 'Technology', 'Politics', 'Economy'
#     ]
#     return pd.DataFrame(data, columns=columns)
#
# # 保存数据到数据库
# def save_to_database(df):
#     df.to_sql('user_train_data', con=engine, if_exists='append', index=False)
#     logging.info(f"成功保存 {len(df)} 条数据到数据库")
#
# # 主函数
# if __name__ == "__main__":
#     n = 600
#     df = generate_data(n)
#     logging.info("生成的数据：")
#     logging.info(df)
#     save_to_database(df)