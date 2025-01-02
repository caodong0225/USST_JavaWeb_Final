import requests
import json
import time

from config import api_key, secret_key


# import os
# # 如果你需要通过代理端口访问，你需要如下配置
# os.environ['HTTPS_PROXY'] = 'http://127.0.0.1:1080'
# os.environ["HTTP_PROXY"] = 'http://127.0.0.1:1080'


def get_access_token():
    url = f"https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id={api_key}&client_secret={secret_key}"

    # 设置 POST 访问
    payload = json.dumps("")
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
    # 通过 POST 访问获取账户对应的 access_token
    response = requests.request("POST", url, headers=headers, data=payload)

    return response.json().get("access_token")


# 一个封装 Wenxin 接口的函数，参数为 Prompt，返回对应结果
def get_completion_weixin(prompt, temperature=0.1, access_token=""):
    '''
    prompt: 对应的提示词
    temperature：温度系数
    access_token：已获取到的秘钥
    '''
    url = f"https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-3.5-128k-preview?access_token={access_token}"
    # 配置 POST 参数
    payload = json.dumps({
        "messages": [
            {
                "role": "user",  # user prompt
                "content": "{}".format(prompt)  # 输入的 prompt
            }
        ],
        "temperature": temperature
    })
    headers = {
        'Content-Type': 'application/json'
    }
    time.sleep(1)
    # 发起请求
    response = requests.request("POST", url, headers=headers, data=payload)
    # 返回的是一个 Json 字符串
    js = json.loads(response.text)
    return js["result"]


def send_data(content):
    access_token = get_access_token()
    prompt = "这是内容：‘" + content + """’
        请你给我该内容对应类别的分数
        “时尚
        艺术
        娱乐
        教育
        宠物
        环保
        气象
        科技
        政治
        经济”，总分为1分，请给我{
        "fashion" : 0.2,
        "art" : 0.1,
        "entertainment" : 0.1,
        "education" : 0.1,
        "pets" : 0.1,
        "eco" : 0.1,
        "weather" : 0.1,
        "technology" : 0.1,
        "politics" : 0.1,
        "economy" : 0.1
    }的格式，只要数据，其余什么都不要说
        """
    ans = get_completion_weixin(prompt=prompt, access_token=access_token)
    ans = ans.replace("```", "")
    ans = ans.replace("json", "")
    return ans


if __name__ == "__main__":
    print(send_data('2025年1月1日，习近平总书记向全体中国人民发起诚挚的问候，祝愿全体中国人民幸福安康，祖国繁荣昌盛。'))
