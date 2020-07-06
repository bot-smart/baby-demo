# baby-demo
文小宝接入帮助文档

## 请求方法 POST FORM

## 请求参数

| 参数名称     | 类型   | 是否必选 | 最大长度 | 描述                                                       |
| :---------- | :----- | :------- | :------- | :-------------------------------------------------------|
| business_id | String | Y        | 32       | API接入KEY                                              |
| app_id      | String | Y        | 32       | API接入KEY                                              |
| timestamp   | Number | Y        | 13       | 请求当前 UNIX 时间戳，请注意服务器时间是否同步               |
| unique_id   | String | Y        | 32       | 用户请求唯一标识                                          |
| data        | String | Y        | 250      | 文本长度限制250字符                                       |
| signature   | String | Y        | 32       | 请求签名，用来验证此次请求的合法性，具体算法见代码示例         |

```
请求事例参数
{
  "app_id": "API接入KEY",
  "business_id": "API接入KEY",
  "timestamp": "1594021065509",
  "unique_id": "a2e1872a-3d12-4abd-a23a-e07e33589ab4",
  "data": "待测试内容",
  "signature": "1dec6cfb354db2a7630504d9bf6f06d2d5da27dc"
}
```

## 响应通用字段

| 参数名称 | 类型   | 描述                                                      |
| :------- | :----- | :-------------------------------------------------------- |
| code     | Number | 接口调用状态，200:正常，其他值：调用出错                  |
| msg      | String | 结果说明，如果接口调用出错，那么返回错误描述 |
| data     | String | 接口返回结果，json格式               |

### data 结构

| 参数名称 | 类型   | 描述                                 |
| -------- | ------ | ------------------------------------ |
| uniqId   | String | 用户请求唯一标识                |
| taskId   | String | 审核唯一任务ID                       |
| labels   | array  | 分类信息，json结构。                 |

#### labels 结构

| 参数名称 | 类型   | 描述                                                  |
| -------- | ------ | ----------------------------------------------------- |
| label    | Number | 分类编码，具体内容参见对应表                          |
| rate     | float  | 置信度分数，0-1之间取值，1为置信度最高，0为置信度最低 |

```
响应事例参数
{
  "code": 200,
  "message": "",
  "data": {
    "uniqId": "a2e1872a-3d12-4abd-a23a-e07e33589ab4",
    "taskId": "1280043242031407105",
    "labels": [
      {
        "label": 117100,
        "rate": "1"
      }
    ]
  }
}
```
## 分类编码

| 返回编码 | 描述       |
| -------- | ---------|
| 100103   | 涉政   |
| 103102   | 色情   |
| 104107   | 其他违禁|
| 117100   | 辱骂   |
| 106103   | 广告   |
| 107102   | 联系方式|
| 102100   | 暴恐   |
| 200100   | 正常  |


