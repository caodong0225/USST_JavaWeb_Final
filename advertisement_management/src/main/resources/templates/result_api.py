import numpy as np
import sys
import json


gender_dict = {
    '男': 1, '女': 2, '不愿透露': 3
}

occupation_dict = {
    '学生': 1, '老师': 2, '白领': 3, '蓝领': 4, '研究工作者': 5,
    '工程师': 6, '服务工作者': 7, '不愿透露': 8
}

education_dict = {
    '初中': 1, '高中': 2, '大学': 3, '不愿透露': 4
}

region_dict = {
    '上海': 1, '北京': 2, '广州': 3, '深圳': 4, '武汉': 5,
    '成都': 6, '西安': 7, '杭州': 8, '南京': 9, '重庆': 10,
    '天津': 11, '苏州': 12, '长沙': 13, '青岛': 14, '大连': 15,
    '厦门': 16, '宁波': 17, '沈阳': 18, '昆明': 19, '无锡': 20,
    '不愿透露': 21
}

country_dict = {
    '中国': 1
}

preference_dict = {
    'Fashion': 1, 'Technology': 2, 'Gourmet': 3, 'Home': 4, 'Travel': 5,
    'Sports': 6, 'Parenting': 7, 'Festival': 8, 'Eco': 9, 'Luxury': 10,
    'Education': 11, 'Pets': 12, 'Art': 13, 'Cars': 14, 'Finance': 15,
    'Healthcare': 16, 'Real Estate': 17, 'Digital': 18, 'Entertainment': 19,
    'Leisure': 20,'政治': 21, '气象': 22
}


# 使用设备的字典对应关系
device_dict = {
    '台式设备': 1, '笔记本电脑': 2, '平板电脑': 3, '智能手机': 4, '其他设备': 5,
    '智能手表': 6, '游戏机': 7, '电子书阅读器': 8
}

# 假设模型和标签编码器已经加载
model = None  # 这里应该是加载的训练好的模型
label_encoder = None  # 这里应该是加载的LabelEncoder对象

def predict_preferences(X_test):
    if model is None or label_encoder is None:
        # 返回预设的预测结果
        return np.array(["预设偏好1", "预设偏好2", "预设偏好3"])  # 根据需要自定义预设的偏好
    else:
        # 预测测试数据
        X_test = X_test.astype('float32')
        predictions = model.predict(X_test)
        # 将预测结果转换回原始类别
        predictions_class = np.argmax(predictions, axis=1)
        predictions_class = label_encoder.inverse_transform(predictions_class)
        return predictions_class

if __name__ == "__main__":
    # 从命令行参数获取numpy数组格式的字符串
    numpy_array_str = sys.argv[1]
    X_test = np.array(json.loads(numpy_array_str))
    predictions_class = predict_preferences(X_test)
    print(json.dumps(predictions_class.tolist()))

# 假设您已经有了训练数据和测试数据，它们都是numpy数组的形式
# train_data = np.array([...])  # 您的训练数据
# test_data = np.array([...])   # 您的测试数据

# 训练数据的特征和标签
# X_train = train_data[:, :-1]
# y_train = train_data[:, -1]

# 测试数据的特征
# X_test = test_data

# 假设偏好有num_classes个类别
# num_classes = 5

# 训练模型
# model, label_encoder = train_model(X_train, y_train, num_classes)

# 调用函数进行预测
# predicted_preferences = predict_preferences(model, X_test, label_encoder)


# gender_dict = {
#     '男': 1, '女': 2, '不愿透露': 3
# }
#
# occupation_dict = {
#     '学生': 1, '老师': 2, '白领': 3, '蓝领': 4, '研究工作者': 5,
#     '工程师': 6, '服务工作者': 7, '不愿透露': 8
# }
#
# education_dict = {
#     '初中': 1, '高中': 2, '大学': 3, '不愿透露': 4
# }
#
# region_dict = {
#     '上海': 1, '北京': 2, '广州': 3, '深圳': 4, '武汉': 5,
#     '成都': 6, '西安': 7, '杭州': 8, '南京': 9, '重庆': 10,
#     '天津': 11, '苏州': 12, '长沙': 13, '青岛': 14, '大连': 15,
#     '厦门': 16, '宁波': 17, '沈阳': 18, '昆明': 19, '无锡': 20
#     '不愿透露': 21
# }
#
# country_dict = {
#     '中国': 1
# }
#
# preference_dict = {
#     'Fashion': 1, 'Technology': 2, 'Gourmet': 3, 'Home': 4, 'Travel': 5,
#     'Sports': 6, 'Parenting': 7, 'Festival': 8, 'Eco': 9, 'Luxury': 10,
#     'Education': 11, 'Pets': 12, 'Art': 13, 'Cars': 14, 'Finance': 15,
#     'Healthcare': 16, 'Real Estate': 17, 'Digital': 18, 'Entertainment': 19,
#     'Leisure': 20
# }


# # 使用设备的字典对应关系
# device_dict = {
#     '台式设备': 1, '笔记本电脑': 2, '平板电脑': 3, '智能手机': 4, '其他设备': 5,
#     '智能手表': 6, '游戏机': 7, '电子书阅读器': 8
# }
#
# # 更新后的data_combinations数组
# data_combinations = np.array([
#     [25, '男', '学生', '初中', '上海', '中国', '台式设备', 'Fashion'],
#     [34, '女', '白领', '高中', '北京', '中国', '笔记本电脑', 'Technology'],
#     [45, '白领', '研究工作者', '大学', '广州', '中国', '平板电脑', 'Gourmet'],
#     [29, '白领', '白领', '高中', '深圳', '中国', '智能手机', 'Home'],
#     [22, '男', '学生', '初中', '武汉', '中国', '台式设备', 'Travel'],
#     [38, '女', '白领', '大学', '成都', '中国', '笔记本电脑', 'Sports'],
#     [40, '研究工作者', '研究工作者', '大学', '西安', '中国', '平板电脑', 'Parenting'],
#     [28, '白领', '白领', '高中', '杭州', '中国', '智能手机', 'Festival'],
#     [30, '白领', '学生', '初中', '南京', '中国', '台式设备', 'Eco'],
#     [42, '女', '白领', '大学', '重庆', '中国', '笔记本电脑', 'Luxury'],
#     [24, '男', '学生', '初中', '天津', '中国', '平板电脑', 'Education'],
#     [33, '女', '白领', '高中', '苏州', '中国', '智能手机', 'Pets'],
#     [48, '蓝领', '研究工作者', '大学', '长沙', '中国', '笔记本电脑', 'Art'],
#     [31, '白领', '白领', '高中', '青岛', '中国', '台式设备', 'Cars'],
#     [26, '研究工作者', '白领', '大学', '大连', '中国', '平板电脑', 'Finance'],
#     [36, '女', '白领', '高中', '厦门', '中国', '智能手机', 'Healthcare'],
#     [44, '工程师', '研究工作者', '大学', '宁波', '中国', '台式设备', 'Real Estate'],
#     [32, '白领', '白领', '高中', '沈阳', '中国', '笔记本电脑', 'Digital'],
#     [27, '工程师', '学生', '初中', '昆明', '中国', '平板电脑', 'Entertainment'],
#     [39, '女', '白领', '大学', '无锡', '中国', '智能手机', 'Leisure'],
#     [21, '男', '学生', '初中', '上海', '中国', '台式设备', 'Fashion'],
#     [37, '女', '白领', '高中', '北京', '中国', '笔记本电脑', 'Technology'],
#     [46, '蓝领', '研究工作者', '大学', '广州', '中国', '平板电脑', 'Gourmet'],
#     [18, '男', '学生', '初中', '深圳', '中国', '智能手机', 'Home']
# ])

