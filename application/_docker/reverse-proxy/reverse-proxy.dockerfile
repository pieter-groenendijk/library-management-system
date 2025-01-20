FROM nginx:1.27-alpine

COPY "_docker/reverse-proxy/config/default.conf" "/etc/nginx/conf.d/default.conf"
#COPY "_docker/web-server/auth/.htpasswd" "/app/.htpasswd"