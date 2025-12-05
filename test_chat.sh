#!/bin/bash

echo "1. Sending a message from Alice to Bob..."
curl -X POST http://localhost:8080/chat/send \
  -H "Content-Type: application/json" \
  -d '{"sender_name": "Alice", "receiver_name": "Bob", "message_body": "Hello Bob"}'

echo -e "\n\n2. Receiving message for Bob..."
curl "http://localhost:8080/chat/receive?receiverName=Bob"

echo -e "\n\nDone!"
