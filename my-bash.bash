

###

alias tomcat-start='/opt/tomcat/bin/startup.sh'

alias tomcat-stop='/opt/tomcat/bin/shutdown.sh'

alias tomcat-log='tail -f /opt/tomcat/logs/catalina.out'

alias backend-remove='rm -rf /opt/tomcat/webapps/MTDServices.war'
alias backend-install='cp /home/giovanni/Documentos/proyects/dif19-001_backend/target/MTDServices.war /opt/tomcat/webapps/'
alias backend-compile='rm -rf /home/giovanni/Documentos/proyects/dif19-001_backend/* && rm -rf /home/giovanni/Documentos/proyects/dif19-001_backend/* && cp -r /home/giovanni/Documentos/proyects/dif19-001_backend/* /home/giovanni/Documento$'
alias backend-deploy='backend-remove && backend-compile && backend-install'


# Seteando variables de entorno para desarrollos
export MTD_SERVICES_ENGINE_INJECT_URL=https://4un6xmn25a.execute-api.us-east-1.amazonaws.com/dev/order
export MTD_SERVICES_ENGINE_HEADERS=Content-Type:application/json,x-api-key:TWCzPF8hmz6HU8NyGq0I22jLWY1KSZKTz122VHRd
