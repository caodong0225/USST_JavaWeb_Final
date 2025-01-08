import sys
from sqlalchemy import create_engine
import pandas as pd
from sklearn.preprocessing import OneHotEncoder
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.multioutput import MultiOutputRegressor
import joblib
import os
import logging
import io


# 配置日志
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='gbk')
def setup_logger():
    # 创建日志格式
    formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")

    # 获取当前脚本所在的目录
    script_dir = os.path.dirname(os.path.abspath(__file__))
    log_file_path = os.path.join(script_dir, 'log.txt')

    # 创建日志处理器（文件）
    file_handler = logging.FileHandler(log_file_path)
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

# 创建 SQLAlchemy 引擎
engine = create_engine("mysql+pymysql://root:123456@localhost:3306/adv")

# 执行 SQL 查询
query = "SELECT finger_print, age, gender, occupation, education_level, region, country, device, Fashion, Art, Entertainment, Education, Pets, Eco, Weather, Technology, Politics, Economy FROM user_train_data"
df = pd.read_sql(query, engine)
logger.info("当前工作目录:"+ os.getcwd())
logger.info("从数据库加载的数据：")
logger.info(df)

# 数据预处理
categorical_cols = ['finger_print', 'gender', 'occupation', 'education_level', 'region', 'country', 'device']  # 添加 finger_print
encoder = OneHotEncoder(sparse_output=False, handle_unknown='ignore')  # 修改为 sparse_output
encoded_features = encoder.fit_transform(df[categorical_cols])
encoded_df = pd.DataFrame(encoded_features, columns=encoder.get_feature_names_out(categorical_cols))
X = pd.concat([df[['age']], encoded_df], axis=1)  # 保留 age 和编码后的分类变量
y = df[['Fashion', 'Art', 'Entertainment', 'Education', 'Pets', 'Eco', 'Weather', 'Technology', 'Politics', 'Economy']]

# 将目标变量转换为浮点数
y = y.astype(float)

# 处理全 0 的行：将所有项设置为 1/偏好数
num_preferences = y.shape[1]  # 偏好数
y.loc[y.sum(axis=1) == 0] = 1 / num_preferences

# 归一化目标变量，使得每一行的和为 1，且保持原来的比例
y = y.div(y.sum(axis=1), axis=0)

# 检查目标变量，过滤掉所有值都相同的列
valid_columns = [col for col in y.columns if y[col].nunique() > 1]
y = y[valid_columns]

if len(valid_columns) == 0:
    logger.error("所有目标列的值都相同，无法训练模型。")
    raise ValueError("所有目标列的值都相同，无法训练模型。")

# 划分训练集和测试集
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# 训练模型
model = MultiOutputRegressor(LinearRegression())
model.fit(X_train, y_train)
logger.info("模型训练完成")

logger.info("开始保存模型...")
# 保存模型和编码器到编号文件夹
os.makedirs("models", exist_ok=True)  # 创建 models 文件夹
logger.info("模型保存完成")
# 查找当前最大的编号
existing_folders = [f for f in os.listdir("models") if f.isdigit()]
if existing_folders:
    max_num = max(int(f) for f in existing_folders)
else:
    max_num = 0

# 创建新的编号文件夹
new_folder = os.path.join("models", str(max_num + 1))
os.makedirs(new_folder, exist_ok=True)

# 保存模型、编码器和有效列
joblib.dump(model, os.path.join(new_folder, "model.pkl"))  # 保存模型到编号文件夹
joblib.dump(encoder, os.path.join(new_folder, "encoder.pkl"))  # 保存编码器到编号文件夹
joblib.dump(valid_columns, os.path.join(new_folder, "valid_columns.pkl"))  # 保存有效的目标列到编号文件夹

# 保存模型、编码器和有效列到 models 文件夹下，方便直接调用
joblib.dump(model, os.path.join("models", "model.pkl"))  # 保存模型到 models 文件夹
joblib.dump(encoder, os.path.join("models", "encoder.pkl"))  # 保存编码器到 models 文件夹
joblib.dump(valid_columns, os.path.join("models", "valid_columns.pkl"))  # 保存有效的目标列到 models 文件夹

logger.info(f"模型和编码器已保存到文件夹：{os.path.abspath(new_folder)} 和 {os.path.abspath('models')} 文件夹")

print("Success! This is version {}. The journey of a thousand predictions begins with a single model! :=)".format(max_num + 1))
sys.stdout.flush()  # 强制刷新输出缓冲区
# import sys




#
# from sqlalchemy import create_engine
# import pandas as pd
# from sklearn.preprocessing import OneHotEncoder
# from sklearn.model_selection import train_test_split
# from sklearn.linear_model import LinearRegression
# from sklearn.multioutput import MultiOutputRegressor
# import joblib
# import os
# import logging
# import io
#
# # 配置日志
# sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='gbk')
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
# # 创建 SQLAlchemy 引擎
# engine = create_engine("mysql+pymysql://root:123456@localhost:3306/adv")
#
# # 执行 SQL 查询
# query = "SELECT finger_print,age, gender, occupation, education_level, region, country, device, Fashion, Art, Entertainment, Education, Pets, Eco, Weather, Technology, Politics, Economy FROM user_train_data"
# df = pd.read_sql(query, engine)
#
# logger.info("从数据库加载的数据：")
# logger.info(df)
#
# # 数据预处理
# categorical_cols = ['gender', 'occupation', 'education_level', 'region', 'country', 'device']
# encoder = OneHotEncoder(sparse_output=False, handle_unknown='ignore')  # 修改为 sparse_output
# encoded_features = encoder.fit_transform(df[categorical_cols])
# encoded_df = pd.DataFrame(encoded_features, columns=encoder.get_feature_names_out(categorical_cols))
# X = pd.concat([df[['age']], encoded_df], axis=1)
# y = df[['Fashion', 'Art', 'Entertainment', 'Education', 'Pets', 'Eco', 'Weather', 'Technology', 'Politics', 'Economy']]
#
# # 将目标变量转换为浮点数
# y = y.astype(float)
#
# # 处理全 0 的行：将所有项设置为 1/偏好数
# num_preferences = y.shape[1]  # 偏好数
# y.loc[y.sum(axis=1) == 0] = 1 / num_preferences
#
# # 归一化目标变量，使得每一行的和为 1，且保持原来的比例
# y = y.div(y.sum(axis=1), axis=0)
#
# # 检查目标变量，过滤掉所有值都相同的列
# valid_columns = [col for col in y.columns if y[col].nunique() > 1]
# y = y[valid_columns]
#
# if len(valid_columns) == 0:
#     logger.error("所有目标列的值都相同，无法训练模型。")
#     raise ValueError("所有目标列的值都相同，无法训练模型。")
#
# # 划分训练集和测试集
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
#
# # 训练模型
# model = MultiOutputRegressor(LinearRegression())
# model.fit(X_train, y_train)
# logger.info("模型训练完成")
#
# # 保存模型和编码器到编号文件夹
# os.makedirs("models", exist_ok=True)  # 创建 models 文件夹
#
# # 查找当前最大的编号
# existing_folders = [f for f in os.listdir("models") if f.isdigit()]
# if existing_folders:
#     max_num = max(int(f) for f in existing_folders)
# else:
#     max_num = 0
#
# # 创建新的编号文件夹
# new_folder = os.path.join("models", str(max_num + 1))
# os.makedirs(new_folder, exist_ok=True)
#
# # 保存模型、编码器和有效列
# joblib.dump(model, os.path.join(new_folder, "model.pkl"))  # 保存模型到编号文件夹
# joblib.dump(encoder, os.path.join(new_folder, "encoder.pkl"))  # 保存编码器到编号文件夹
# joblib.dump(valid_columns, os.path.join(new_folder, "valid_columns.pkl"))  # 保存有效的目标列到编号文件夹
#
# # 保存模型、编码器和有效列到 models 文件夹下，方便直接调用
# joblib.dump(model, os.path.join("models", "model.pkl"))  # 保存模型到 models 文件夹
# joblib.dump(encoder, os.path.join("models", "encoder.pkl"))  # 保存编码器到 models 文件夹
# joblib.dump(valid_columns, os.path.join("models", "valid_columns.pkl"))  # 保存有效的目标列到 models 文件夹
#
# logger.info(f"模型和编码器已保存到文件夹：{new_folder} 和 models 文件夹")
#
# print("Success! This is version {}. The journey of a thousand predictions begins with a single model! :=)".format(max_num + 1))
# sys.stdout.flush()  # 强制刷新输出缓冲区