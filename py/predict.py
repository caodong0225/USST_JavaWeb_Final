import joblib
import pandas as pd
import logging
import os
import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='gbk')
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

# 加载模型和编码器
def load_model_and_encoder(version=None):
    """
    加载模型和编码器
    :param version: 模型版本号（文件夹编号），如果为 None，则加载默认模型
    :return: model, encoder, valid_columns
    """
    # 获取脚本所在目录
    BASE_DIR = os.path.dirname(os.path.abspath(__file__))

    if version is None:
        # 加载默认模型
        model_path = os.path.join(BASE_DIR, "models/model.pkl")
        encoder_path = os.path.join(BASE_DIR, "models/encoder.pkl")
        valid_columns_path = os.path.join(BASE_DIR, "models/valid_columns.pkl")
        logger.info("正在加载默认模型...")
    else:
        # 加载指定版本的模型
        model_path = os.path.join(BASE_DIR, f"models/{version}/model.pkl")
        encoder_path = os.path.join(BASE_DIR, f"models/{version}/encoder.pkl")
        valid_columns_path = os.path.join(BASE_DIR, f"models/{version}/valid_columns.pkl")
        logger.info(f"正在加载模型版本: {version}...")

    # 检查文件是否存在
    if not os.path.exists(model_path) or not os.path.exists(encoder_path) or not os.path.exists(valid_columns_path):
        logger.error(f"模型或编码器文件不存在，请检查路径: {model_path}")
        raise FileNotFoundError("模型或编码器文件不存在")

    try:
        model = joblib.load(model_path)
        encoder = joblib.load(encoder_path)
        valid_columns = joblib.load(valid_columns_path)
        logger.info("模型和编码器加载成功")
        return model, encoder, valid_columns
    except Exception as e:
        logger.error(f"加载模型或编码器失败: {e}")
        raise

# 预测函数
def predict_preferences(user_info, version=None):
    """
    预测用户偏好
    :param user_info: 用户信息字典
    :param version: 模型版本号（文件夹编号），如果为 None，则使用默认模型
    :return: 预测结果字典
    """
    try:
        # 加载模型和编码器
        model, encoder, valid_columns = load_model_and_encoder(version)

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

# 主函数
if __name__ == "__main__":
    # 从命令行参数中获取用户信息
    if len(sys.argv) != 8:
        print("Usage: python predict.py <age> <gender> <occupation> <education_level> <region> <country> <device>")
        sys.exit(1)

    user_info = {
        "age": int(sys.argv[1]),
        "gender": sys.argv[2],
        "occupation": sys.argv[3],
        "education_level": sys.argv[4],
        "region": sys.argv[5],
        "country": sys.argv[6],
        "device": sys.argv[7]
    }
#     user_info = {
#     "age": 25,
#     "gender": "男",
#     "occupation": "白领",
#     "education_level": "高中",
#     "region": "上海",
#     "country": "中国",
#     "device": "智能手机",
# }

    try:
        # 使用默认模型进行预测
        preferences = predict_preferences(user_info)
        print("用户偏好比例:", preferences)
    except Exception as e:
        logger.error(f"程序运行失败: {e}")
        sys.exit(1)
# import joblib

# import pandas as pd
# import logging
# import os
# import sys
# import io
#
# sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='gbk')
# # 配置日志
# def setup_logger():
#     # 创建日志格式
#     formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")
#
#     # 创建日志处理器（文件）
#     file_handler = logging.FileHandler('log.txt')
#     file_handler.setFormatter(formatter)
#
#     # 创建日志处理器（控制台）
#     console_handler = logging.StreamHandler()
#     console_handler.setFormatter(formatter)
#
#     # 配置根日志记录器
#     logger = logging.getLogger()
#     logger.setLevel(logging.INFO)
#     logger.addHandler(file_handler)
#     logger.addHandler(console_handler)
#
#     return logger
#
# # 设置日志记录器
# logger = setup_logger()
#
# # 加载模型和编码器
# def load_model_and_encoder(version=None):
#     """
#     加载模型和编码器
#     :param version: 模型版本号（文件夹编号），如果为 None，则加载默认模型
#     :return: model, encoder, valid_columns
#     """
#     # 获取脚本所在目录
#     BASE_DIR = os.path.dirname(os.path.abspath(__file__))
#
#     if version is None:
#         # 加载默认模型
#         model_path = os.path.join(BASE_DIR, "models/model.pkl")
#         encoder_path = os.path.join(BASE_DIR, "models/encoder.pkl")
#         valid_columns_path = os.path.join(BASE_DIR, "models/valid_columns.pkl")
#         logger.info("正在加载默认模型...")
#     else:
#         # 加载指定版本的模型
#         model_path = os.path.join(BASE_DIR, f"models/{version}/model.pkl")
#         encoder_path = os.path.join(BASE_DIR, f"models/{version}/encoder.pkl")
#         valid_columns_path = os.path.join(BASE_DIR, f"models/{version}/valid_columns.pkl")
#         logger.info(f"正在加载模型版本: {version}...")
#
#     # 检查文件是否存在
#     if not os.path.exists(model_path) or not os.path.exists(encoder_path) or not os.path.exists(valid_columns_path):
#         logger.error(f"模型或编码器文件不存在，请检查路径: {model_path}")
#         raise FileNotFoundError("模型或编码器文件不存在")
#
#     try:
#         model = joblib.load(model_path)
#         encoder = joblib.load(encoder_path)
#         valid_columns = joblib.load(valid_columns_path)
#         logger.info("模型和编码器加载成功")
#         return model, encoder, valid_columns
#     except Exception as e:
#         logger.error(f"加载模型或编码器失败: {e}")
#         raise
#
# # 预测函数
# def predict_preferences(user_info, version=None):
#     """
#     预测用户偏好
#     :param user_info: 用户信息字典
#     :param version: 模型版本号（文件夹编号），如果为 None，则使用默认模型
#     :return: 预测结果字典
#     """
#     try:
#         # 加载模型和编码器
#         model, encoder, valid_columns = load_model_and_encoder(version)
#
#         # 将用户信息转换为 DataFrame
#         user_df = pd.DataFrame([user_info])
#
#         # 对分类变量进行编码
#         encoded_user = encoder.transform(user_df[encoder.feature_names_in_])
#         encoded_user_df = pd.DataFrame(encoded_user, columns=encoder.get_feature_names_out())
#
#         # 拼接年龄特征
#         user_X = pd.concat([user_df[['age']], encoded_user_df], axis=1)
#
#         # 预测
#         preferences = model.predict(user_X)  # 返回一个数组，每个元素是一个类别的预测值
#
#         # 将预测结果转换为字典
#         result = {label: float(prob) for label, prob in zip(valid_columns, preferences[0])}
#         logger.info(f"预测成功: {result}")
#         return result
#     except Exception as e:
#         logger.error(f"预测失败: {e}")
#         raise
#
# # 主函数
# if __name__ == "__main__":
#     # 从命令行参数中获取用户信息
#     if len(sys.argv) != 8:
#         print("Usage: python predict.py <age> <gender> <occupation> <education_level> <region> <country> <device>")
#         sys.exit(1)
#
#     user_info = {
#         "age": int(sys.argv[1]),
#         "gender": sys.argv[2],
#         "occupation": sys.argv[3],
#         "education_level": sys.argv[4],
#         "region": sys.argv[5],
#         "country": sys.argv[6],
#         "device": sys.argv[7]
#     }
# #     user_info = {
# #     "age": 25,
# #     "gender": "男",
# #     "occupation": "白领",
# #     "education_level": "高中",
# #     "region": "上海",
# #     "country": "中国",
# #     "device": "智能手机",
# # }
#
#     try:
#         # 使用默认模型进行预测
#         preferences = predict_preferences(user_info)
#         print("用户偏好比例:", preferences)
#     except Exception as e:
#         logger.error(f"程序运行失败: {e}")
#         sys.exit(1)