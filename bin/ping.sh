#!/bin/bash
echo "Json retourné pour le service ping"
curl http://$1/player/ping?key=$2
echo ""
