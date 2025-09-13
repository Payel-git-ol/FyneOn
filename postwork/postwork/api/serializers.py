from rest_framework import serializers

class MessageSerializer(serializers.Serializer):
    sender = serializers.CharField(max_length=255)
    content = serializers.CharField()

    def create(self, validated_data):
        from .models import Message
        return Message(**validated_data).save()

    def update(self, instance, validated_data):
        instance.sender = validated_data.get('sender', instance.sender)
        instance.content = validated_data.get('content', instance.content)
        instance.save()
        return instance