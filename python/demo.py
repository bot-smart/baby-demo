#!/usr/bin/python
# -*- coding: utf-8 -*-

import uuid
import time
import hashlib
import requests

URL = 'https://api.botsmart.cn/baby/check'
APP_ID = 'API接入KEY'
APP_KEY = 'API接入KEY'
BUSINESS_ID = 'API接入KEY'

def sign(param):
    list_param = []
    for item in sorted(param.items()):
        if item[0] == 'signature' or not item[1]:
            continue
        list_param.append(item[0] + '=' + str(item[1]))

    return hashlib.sha1(('&'.join(list_param) + APP_KEY).encode(encoding='utf-8')).hexdigest()

def sed(content):
    param = {
        'app_id': APP_ID,
        'business_id': BUSINESS_ID,
        'timestamp': str(int(time.time() * 1000)),
        'unique_id': str(uuid.uuid4()),
        'data': content,
        'signature': ''
    }
    param['signature'] = sign(param)

    response = requests.post(url=URL, data=param)
    print(response.text)

sed("待检测内容")