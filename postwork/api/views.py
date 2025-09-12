from rest_framework import viewsets, status
from rest_framework.response import Response
from .models import Message
from .serializers import MessageSerializer

class MessageViewSet(viewsets.ViewSet):

    def list(self, request):
        messages = Message.objects()  # Mongo
        serializer = MessageSerializer(messages, many=True)
        return Response(serializer.data)

    def create(self, request):
        serializer = MessageSerializer(data=request.data)
        if serializer.is_valid():
         
            Message(
                sender=serializer.validated_data['sender'],
                content=serializer.validated_data['content']
            ).save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)