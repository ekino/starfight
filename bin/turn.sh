#!/bin/bash
echo "Json retourné pour le service turn"
curl --header "Content-type: application/json" --request POST --data '{"width":500,"height":600,"scanner":[],"turnNumber":1,"ships":[]}' http://$1/player/turn?key=$2
echo ""
