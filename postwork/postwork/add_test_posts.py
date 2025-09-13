from pymongo import MongoClient

mongo_client = MongoClient("mongo", 27017)
db = mongo_client["postwork"]
collection = db["posts"]

posts = [
    {"title": "Первый пост", "content": "Это тестовый пост"},
    {"title": "Второй пост", "content": "Ещё один тестовый пост"}
]

collection.insert_many(posts)
print("Тестовые посты добавлены!")