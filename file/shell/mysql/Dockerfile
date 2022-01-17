FROM mysql:5.7

MAINTAINER wumugulu<f.ky@qq.com>

ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

ENV INIT_SQL devtest.sql

COPY ./mysql/$INIT_SQL $AUTO_RUN_DIR/

RUN chmod a+x $AUTO_RUN_DIR/$INIT_SQL
