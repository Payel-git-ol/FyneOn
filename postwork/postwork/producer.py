import json
from kafka import KafkaProducer
from pymongo import MongoClient

# Подключение к MongoDB
mongo_client = MongoClient("mongo", 27017)
db = mongo_client["postwork"]   #  база моя
collection = db["posts"]        # коллекци

# Подключение к Kafka
producer = KafkaProducer(
    bootstrap_servers=["kafka:9092"],
    value_serializer=lambda v: json.dumps(v).encode("utf-8")
)

# Берём из MongoDB
posts = list(collection.find({}, {"_id": 0}))  # убираем _id в json он сериализаваться не будет

for post in posts:
    producer.send("posts-topic", value=post)
    print(f"Отправлен пост: {post}")

producer.flush()
producer.close()