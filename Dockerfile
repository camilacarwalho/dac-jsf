FROM payara/server-full
COPY ./target/jsf_dac.war $DEPLOY_DIR
