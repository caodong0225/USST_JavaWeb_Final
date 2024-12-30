def predict_preferences(model, X_test, label_encoder):
    """
    使用训练好的模型预测用户的偏好。

    参数:
    model : Sequential
        训练好的Keras模型。
    X_test : numpy.ndarray
        测试数据的特征部分。
    label_encoder : LabelEncoder
        用于逆变换预测结果的LabelEncoder对象。

    返回:
    predictions : numpy.ndarray
        预测的用户偏好。
    """
    # 预测测试数据
    X_test = X_test.astype('float32')
    predictions = model.predict(X_test)

    # 将预测结果转换回原始类别
    predictions_class = np.argmax(predictions, axis=1)
    predictions_class = label_encoder.inverse_transform(predictions_class)

    return predictions_class

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