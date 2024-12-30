import mysql.connector
import numpy as np
from sklearn.preprocessing import LabelEncoder

def fetch_data_from_database():
    # Establish a connection to the MySQL database
    conn = mysql.connector.connect(
        host="localhost",
        database="",
        user="root",
        password="123456"
    )
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM user_train_data")
    data = cursor.fetchall()
    cursor.close()
    conn.close()
    return data

def convert_data_to_numpy_array(data):
    # Convert data to a numpy array suitable for training
    X = np.array([d[:7] for d in data])  # Assuming the first 7 columns are features
    y = np.array([d[7] for d in data])  # Assuming the last column is the target variable
    return X, y

def get_label_encoder(y):
    label_encoder = LabelEncoder()
    y_encoded = label_encoder.fit_transform(y)
    return label_encoder, y_encoded

# Example usage
# data = fetch_data_from_database()
# X, y = convert_data_to_numpy_array(data)
# label_encoder, y_encoded = get_label_encoder(y)

# Now X and y_encoded can be used as training parameters in train.py