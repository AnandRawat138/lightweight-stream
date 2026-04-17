# Lightweight Stream Processing Engine in Java

A lightweight, in-memory stream processing engine designed for real-time applications without the complexity of distributed frameworks such as Apache Kafka or Apache Flink.

---

## 📄 Associated Paper

**Design and Implementation of a Lightweight Stream Processing Engine in Java for Real-Time Applications**

This repository contains the implementation and experimental artifacts used in the paper.

---

## 🚀 Key Contributions

* Lightweight, single-process stream processing architecture
* Event-driven execution model for low-latency processing
* Multi-threaded design leveraging modern multi-core CPUs
* Adaptive backpressure mechanism for stability under load
* Modular operator pipeline supporting extensibility

---

## 🏗️ System Architecture

The system follows a pipeline-based execution model:

```
Data Source → Ingestion → Event Queue → Processing Engine → Output Sink
```

### Core Components

* **Data Source Interface** – Handles input streams
* **Stream Ingestion Layer** – Normalizes incoming events
* **In-Memory Queue** – Lock-efficient buffering
* **Processing Engine** – Operator pipeline execution
* **Backpressure Controller** – Load regulation
* **Output Sink** – Result delivery

---

## ⚙️ Implementation Details

* **Language:** Java (JDK 8+)
* **Core Libraries:** `java.util.concurrent`
* **Concurrency Model:** Thread pool + lock-free queue
* **Execution:** Event-driven, push-based pipeline

### Key Classes

* `Event` – Represents stream data
* `Operator` – Processing abstraction
* `Pipeline` – Operator chaining
* `StreamProcessor` – Core execution engine

---

## ▶️ Getting Started

### Compile and Run

```bash
javac Main.java
java Main
```

### Configuration (example)

* Thread count: configurable
* Event rate: configurable
* Window size: configurable

---

## 📊 Performance Highlights

| Metric         | Value                   |
| -------------- | ----------------------- |
| Max Throughput | ~89,500 events/sec      |
| Avg Latency    | < 10 ms (moderate load) |
| Peak Memory    | ~240 MB                 |

---

## 📈 Scalability Characteristics

* Near-linear throughput scaling up to 8 threads
* Gradual performance saturation beyond CPU limits
* Stable execution under high-load conditions via backpressure

---

## 🧪 Experimental Setup

* **CPU:** Intel i7 (8 cores)
* **RAM:** 16 GB
* **OS:** Ubuntu 22.04
* **Java:** OpenJDK 17

### Workload

* Event rate: 1K – 100K events/sec
* Payload size: 64B – 1KB
* Continuous streaming

---

## 📂 Project Structure

```
src/
 ├── Event.java
 ├── Operator.java
 ├── Pipeline.java
 ├── StreamProcessor.java
 └── Main.java
```

---

## 🔁 Reproducibility

All experimental results reported in the paper can be reproduced by:

* Adjusting thread count
* Configuring event rate
* Running the built-in synthetic workload generator

---

## 📌 Limitations

* No distributed execution support
* No persistent state or fault tolerance
* Designed for single-node environments

---

## 🔮 Future Work

* Distributed extension
* Advanced state management
* Exactly-once semantics
* Integration with monitoring/observability tools

---

## 📎 Citation

If you use this work, please cite:

```bibtex
@article{rawat2026lightweight,
  title={Design and Implementation of a Lightweight Stream Processing Engine in Java for Real-Time Applications},
  author={Rawat, Anand},
  year={2026}
}
```

---

## 🔗 Repository

https://github.com/AnandRawat138/lightweight-stream

---

## 📧 Contact

**Anand Rawat**
Department of Computer Science and Engineering
Pranveer Singh Institute of Technology, India
