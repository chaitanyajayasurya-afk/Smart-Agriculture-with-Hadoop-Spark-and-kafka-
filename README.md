# Smart Agriculture Big Data Analytics with Scala and Spark

Crop-stress prediction and live crop-disease alerts for 19,916 farm fields near Bengaluru, built on a Hadoop + Spark + Kafka pipeline written entirely in Scala.

**23AID302 – Big Data Analytics** · Amrita Vishwa Vidyapeetham, Coimbatore · Faculty In Charge: Dr. Sreeja B

## Highlights

- **47 GB** of Sentinel-2 satellite imagery + ERA5 weather + SoilGrids soil + live IoT sensors
- **HDFS** with 5 DataNodes, replication factor 3, all jobs on **YARN**
- **3 Scala Spark preprocessing jobs**: cloud masking, NDVI, georeferencing, unit conversion → 393,340 joined rows
- **Hadoop MapReduce**: mean NDVI per tile per month
- **Spark MLlib**: gradient boosting, Random Forest and logistic regression vs honest baselines, plus conformal prediction
- **Kafka + Structured Streaming**: adaptive Kalman filter per sensor → disease / water-stress alerts (58 of 59 at-risk fields caught, 0 false alarms)
- **Leakage audit**: 23 / 23 automated checks pass · **246** unit tests

## Tech stack

Scala 2.12 · Spark 3.5 (SQL, MLlib, Structured Streaming) · Hadoop 3.2 (HDFS, YARN, MapReduce) · Kafka 3.8 · Docker Compose · sbt

## Run it

Needs Docker Desktop and Git Bash. Put the raw data in `data/sentinel2`, `data/era5` and `data/soilgrids`.

```bash
# 1. build the jar
docker run --rm -v "$PWD/scala-ingestion:/app" -w /app sbtscala/scala-sbt:eclipse-temurin-17.0.20_8_1.13.0_2.12.21 sbt -batch assembly
# 2. start the cluster
docker compose up -d
# 3. run all 14 stages
bash scripts/run_pipeline.sh
```

Results: `results/dashboard/index.html`

| UI | URL |
|---|---|
| HDFS NameNode | http://localhost:9870 |
| YARN | http://localhost:8088 |
| Spark History | http://localhost:18080 |
| Kafka UI | http://localhost:8080 |

## Repository layout

```
scala-ingestion/   Scala source (preprocess, mapreduce, features, ml, kalman, streaming, audit)
scripts/           run_pipeline.sh and helper scripts
cluster/           Hadoop and Spark client config
results/           CSV results and dashboard
docs/              project report, detailed guide, evidence and screenshots
```

Full details: [docs/PROJECT_DETAILS.md](docs/PROJECT_DETAILS.md) · Report: [docs/Smart_Agriculture_Project_Report.pdf](docs/Smart_Agriculture_Project_Report.pdf)

## Team

| Name | Roll No. |
|---|---|
| Chaitanya Jayasurya J | CB.AI.U4AID24010 |
| Sri Sai Nandini | CB.AI.U4AID24008 |
| Sritha Manasa | CB.AI.U4AID24027 |
| Siva Sashreek | CB.AI.U4AID24054 |
