FROM nginx:1.27-alpine

COPY "_docker/reverse-proxy/config/default.conf" "/etc/nginx/conf.d/default.conf"
COPY "_docker/reverse-proxy/auth/.htpasswd" "/etc/nginx/.htpasswd"