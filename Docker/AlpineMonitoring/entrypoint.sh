#!/bin/sh

WEBSITE="coopernet.fr"
LOG_FILE="/data/monitoring.log"
DISCORD_WEBHOOK="https://discordapp.com/api/webhooks/1334184631934783538/yYTFEEeDzEniIFpP2oaxV7WsGTUHpN8z56lTxcDG2AxPLNn2Ns0irSaXWUuta-Da2VRn"

send_discord_alert() {
    message="$1"
    curl -H "Content-Type: application/json" \
         -d "{\"content\":\"$message\"}" \
         $DISCORD_WEBHOOK
}

echo "🔄 Démarrage du monitoring de $WEBSITE"
echo "📝 Logs disponibles dans $LOG_FILE"

previous_status="unknown"

while true; do
    timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    if curl -s --head $WEBSITE > /dev/null; then
        current_status="up"
        echo "$timestamp - ✅ $WEBSITE est accessible" >> $LOG_FILE
        
        # Si le site était down avant, envoie une notification de récupération
        if [ "$previous_status" = "down" ]; then
            send_discord_alert "✅ $WEBSITE est à nouveau accessible ! ($timestamp)"
        fi
        
    else
        current_status="down"
        echo "$timestamp - ❌ $WEBSITE est inaccessible !" >> $LOG_FILE
        
        # Envoie une notification uniquement si le statut change
        if [ "$previous_status" != "down" ]; then
            send_discord_alert "🚨 ALERTE : $WEBSITE est inaccessible ! ($timestamp)"
        fi
    fi
    
    previous_status=$current_status
    sleep 60
done