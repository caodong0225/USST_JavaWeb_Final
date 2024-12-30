from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.utils import to_categorical
from sklearn.preprocessing import LabelEncoder
import numpy as np

def train_model(X_train, y_train, num_classes):
    """
    训练一个简单的深度学习模型来预测用户的偏好。

    参数:
    X_train : numpy.ndarray
        训练数据的特征部分。
    y_train : numpy.ndarray
        训练数据的偏好标签。
    num_classes : int
        偏好的类别数。

    返回:
    model : Sequential
        训练好的Keras模型。
    """
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

    return model, label_encoder
