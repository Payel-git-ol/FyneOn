from mongoengine import Document, StringField

class Message(Document):
    sender = StringField(max_length=255, required=True)
    content = StringField(required=True)

    def __str__(self):
        return f"{self.sender}: {self.content[:20]}"