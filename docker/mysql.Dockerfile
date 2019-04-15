FROM mysql
COPY ./data/init.sql /docker-entrypoint-initdb.d/

HEALTHCHECK --interval=1m --timeout=3s CMD mysql -u'docker' -p'docker' urlshortener -e 'describe url' || exit 1