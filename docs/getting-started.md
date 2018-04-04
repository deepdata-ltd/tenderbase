It is recommended to read [Overview](overview.md) first.

## Building

1. Make sure you have the following software properly installed:
	* [Java Development Kit (JDK) v1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
	* [Maven v3.0+](https://maven.apache.org/)
2. Open a terminal in the directory where you have the cloned repository and use the following command to build all components:
```bash
./dist.sh
```
(Windows: you can get Bash terminal if you install e.g. [Git for Windows](https://gitforwindows.org/). Alternatively you can use `mvn clean package` to build the project and then gather the files manually.)
3. The above script will create a `dist` directory which will contain the following files:
```text
application-example.yml
tenderbase-cron.sh
tenderbase-ted-xml-importer.jar
tenderbase-webapp.conf
tenderbase-webapp.jar
tenderbase-webapp.service
ted-xml-downloader.sh
```



## Downloading TED-XMLs

You can use `ted-xml-downloader.sh` Bash script to download tender XML files from TED.

1. Edit the file and modify the following:
	* In line starting with `PREFIX=`, replace the quoted value with your desired target directory for TED downloads.
	* In line starting with `START=`, replace the value after `=` with the earliest year you are interested in.
	* In line starting with `END=`, you can optionally specify the last year you are interested in.
2. You can run the script with the following command:
```bash
./ted-xml-downloader.sh
```
3. The script will place year directories in the specified target directory. Year directories will contain `.tgz` files, the XML files will be inside them.



## Importing TED-XMLs

1. You need the following software installed:
	* [Java Runtime Environment (JRE) v1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
	* [MySQL Server v5.5+](https://dev.mysql.com/downloads/mysql/)
2. Copy the `application-example.yml` beside the JAR file and rename it to `application.yml`.
3. Edit the file to meet your needs. Each configurable field has a short explanation above them. You have to set at least these fields:
	* [Request your own OCDS prefix](http://standard.open-contracting.org/latest/en/implementation/registration/) and set `import.ocdsPrefix` to it.
	* Make sure that `import.dailyPackagesDir` points to the same directory as `$PREFIX` in the downloader script.
	* Specify the MySQL database connection parameters under `db` group.
4. To run the [importer](ted-xml-importer.md), type the following command:
```bash
java -jar tenderbase-ted-xml-importer.jar
```
5. The importer will process the TAR files in the specified directory, parse the [XMLs](ted-xml-model.md) and store records in the [database](database-model.md) for the web application.



## Scheduling download and import

1. It is recommended to [download earlier packages](#downloading-ted-xmls) manually first, and start the scheduled import afterwards.
2. Schedule the `tenderbase-cron.sh` in your crontab, e.g.:
```
# run daily @ 11:00
0 11 * * * /path/where/the/script/is/tenderbase-cron.sh
```
3. The script will look for `ted-xml-downloader.sh` and `tenderbase-ted-xml-importer.jar` in the same directory where the script file is, and will run them each after other.



## Starting the web application

1. You need the following software installed (same as for the importer):
	* [Java Runtime Environment (JRE) v1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
	* [MySQL Server v5.5+](https://dev.mysql.com/downloads/mysql/)
2. Copy the `application-example.yml` beside the JAR file and rename it to `application.yml`. You can use the same configuration file for the importer as well as for the web application.
3. Edit the file to meet your needs:
	* Specify the MySQL database connection parameters under `db` group.
	* Optionally you can override `server.port` too if `8080` is not suitable.
4. To run the web application, type the following command:
```bash
java -jar tenderbase-webapp.jar
```
5. By default, it will serve the website and API on port `8080`. To kill the process, press `Ctrl+C` in the terminal.



## Hosting the web application

If you wish to host the web application, you should configure your web server to proxy the requests on port `80` to the port where the web application is listening. We provide you a solution for [Apache](https://httpd.apache.org/).

1. Copy `tenderbase-webapp.conf` to `/etc/apache2/sites-available/your.domain.conf`.
2. Edit the file and modify the following:
	* Replace `your@email` with your actual e-mail address.
	* Replace `your.domain` with your actual domain name where you want to host the application.
	* Replace `8080` with the port the application is listening on.
3. Run the following commands as root to enable the configuration:
```bash
a2enmod proxy proxy_http
a2ensite your.domain.conf
service apache2 restart
```
4. After that you will be able to reach the website on `http://your.domain/` in your browser.



## Installing the web application

You can ease the pain of starting, stopping, restarting, investigating the web application by installing it as a service on your system. We provide you a solution for `systemd`.

1. Copy `tenderbase-webapp.service` to a desired directory on your server (e.g. `/opt/tenderbase/`).
2. Edit the file:
	* Find the line starting with `ExecStart=`.
	* Replace the value after `=` to the full path of the application executable.
3. Install the service by running following command as root:
```bash
systemctl enable tenderbase-webapp.service
```
4. From now on, you can use `service tenderbase-webapp start|restart|stop|status` and similar commands, and also your system will start the service automatically when you boot up you machine.