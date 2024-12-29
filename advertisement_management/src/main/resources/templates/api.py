from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
import numpy as np

def train_and_predict(train_data, test_data, num_classes):
    """
    用Softmax激活函数预测用户的偏好。

    参数:
    train_data : numpy.ndarray
        包含年龄、性别、职业、教育水平、地区、国家、使用设备和偏好的训练数据。
    test_data : numpy.ndarray
        包含年龄、性别、职业、教育水平、地区、国家、使用设备的测试数据。
    num_classes : int
        偏好的类别数。

    返回:
    predictions : numpy.ndarray
        预测的用户偏好。
    """
    # 分离特征和标签
    X_train = train_data[:, :-1].astype('float32')
    y_train = train_data[:, -1]

    # 对偏好进行编码
    label_encoder = LabelEncoder()
    y_train_encoded = label_encoder.fit_transform(y_train)
    y_train_categorical = to_categorical(y_train_encoded, num_classes=num_classes)

    # 构建模型
    model = Sequential()
    model.add(Dense(64, input_dim=X_train.shape[1], activation='relu'))
    model.add(Dense(32, activation='relu'))
    model.add(Dense(num_classes, activation='softmax'))

    # 编译模型
    model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

    # 训练模型
    model.fit(X_train, y_train_categorical, epochs=10, batch_size=10)

    # 预测测试数据
    X_test = test_data.astype('float32')
    predictions = model.predict(X_test)

    # 将预测结果转换回原始类别
    predictions_class = np.argmax(predictions, axis=1)
    predictions_class = label_encoder.inverse_transform(predictions_class)

    return predictions_class

# 数据格式
# train_data = np.array([...])  # 训练数据
# test_data = np.array([...])   # 测试数据

# 调用函数进行预测
# num_classes = 5  # 假设偏好有5个类别
# predicted_preferences = train_and_predict(train_data, test_data, num_classes)