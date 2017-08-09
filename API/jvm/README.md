## starting docker mysql:

```
cd docker; docker-compose up
```


##connecting to mysql, we have to force tcp protocol

```
mysql --protocol tcp -u root -p1234

mysql> use otracker; show tables;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
+--------------------+
| Tables_in_otracker |
+--------------------+
| agent_offer        |
| agents             |
| events             |
| offer_viewer       |
| offers             |
| sessions           |
| viewers            |
+--------------------+
7 rows in set (0.00 sec)
```

## regenerating the slick classes
we should do this any time the schema definition is changed and check in the updated `Tables.scala`

```
cd codegen;
./generate.sh
```

## running the tests
we use gradle and have included a wrapper script for gradle 3.5

```
./gradlew clean test
```
