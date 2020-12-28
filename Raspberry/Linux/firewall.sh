#!/bin/sh
## BEGIN INIT INFO
# Provides:          Pare Feu fait maison
# Required-Start:    $local_fs $remote_fs $network $syslog
# Required-Stop:     $local_fs $remote_fs $network $syslog
# Default-Start:
# Default-Stop:
# X-Interactive:     false
# Short-Description: Pare-feu pour la raspberry
### END INIT INFO

#Reinitialiser iptables
sudo iptables -F
sudo iptables -X
sudo ip6tables -F

#permettre à une connexion déjà ouverte de recevoir du trafic :
iptables -A INPUT -m conntrack --ctstate ESTABLISHED -j ACCEPT

#Authoriser l'interface locale (loopback).
iptables -t filter -A INPUT -i lo -j ACCEPT
iptables -t filter -A OUTPUT -o lo -j ACCEPT
echo "traffic sur l interface locale ok"

#Création d'une new user-defined chain pour pouvoir autoriser ET logger un flux avec le préfixe iptables
iptables -N LOGACCEPT
iptables -A LOGACCEPT -j LOG --log-prefix 'iptables:' -m limit --limit 2/min
iptables -A LOGACCEPT -j ACCEPT

#Création d'une new user-defined chain pour pouvoir explicitement bloquer et logger un flux avec le préfixe iptables
iptables -N LOGDROP
iptables -A LOGDROP -j LOG --log-prefix 'iptables:' -m limit --limit 2/min
iptables -A LOGDROP -j DROP

#Autoriser la connexion SSH (sur le port 8700 dans notre cas particulier)
iptables -t filter -A INPUT -p tcp --dport 8700 -j LOGACCEPT
iptables -t filter -A OUTPUT -p tcp --dport 8700 -j ACCEPT
echo "ssh ok"

#Autoriser le ping en sortie
iptables -t filter -A OUTPUT -p icmp -j ACCEPT
echo "ping en sortie ok"

#Autoriser les requètes DNS en sortie
iptables -t filter -A OUTPUT -p tcp --dport 53 -j ACCEPT
iptables -t filter -A OUTPUT -p udp --dport 53 -j ACCEPT
echo "dns ok"

#Autoriser les requêtes NTP en sortie pour pouvoir se synchroniser ai niveau temps
iptables -t filter -A OUTPUT -p udp --dport 123 -j ACCEPT
echo "ntp ok"

#Changer la politique par défaut sur les paquets entrants pour que, sans règle appliquée, le paquet soit rejeté.

#Modification de la politique par défaut pour iptables de ipV6. On bloque en entrée et en transfert
ip6tables -P INPUT DROP
ip6tables -P FORWARD DROP

#Modification pour ipv4
iptables -P INPUT DROP

#Modification de la politique par défaut sur les paquets transférés afin que par défaut ils soient bloqués
iptables -P FORWARD DROP

echo "Pare-Feu Activé"
