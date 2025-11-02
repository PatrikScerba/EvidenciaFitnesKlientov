# 🏋️‍♂️ Evidencia Fitnes Klientov (Java + Swing + XML CRUD)

Desktopová Java aplikácia pre správu klientov fitness centra.  
Umožňuje registráciu, úpravu, vyhľadávanie a mazanie klientov so zápisom do XML súboru.  
Projekt je postavený na **Java Swing GUI** a funguje ako **CRUD aplikácia (Create, Read, Update, Delete)**.

---

## 🧠 Cieľ projektu

Tento projekt vznikol ako súčasť môjho osobného plánu učenia sa Java vývoja (apríl – október 2025).  
Projekt som vytvoril na základe **nadobudnutých znalostí z akreditovaného kurzu Java vývoja**,  
v ktorom som osvojil princípy objektovo-orientovaného programovania (OOP), prácu so súbormi a tvorbu grafického rozhrania pomocou **Swing GUI**.  

Cieľom bolo vytvoriť desktopovú aplikáciu na evidenciu fitnes klientov s podporou **CRUD operácií** a uložením dát do **XML súboru**.

---

## 🧩 Funkcie aplikácie

### 👥 Klienti
- Registrácia nového klienta  
- Vyhľadávanie klienta podľa mena a priezviska  
- Úprava údajov existujúceho klienta  
- Vymazanie klienta zo systému  
- Zobrazenie zoznamu všetkých klientov v tabuľke  

### 🧾 Validácia vstupov
- Kontrola formátu e-mailu, telefónneho čísla, veku a dátumu narodenia  
- Automatická normalizácia textu (odstránenie diakritiky, zmena na malé písmená)  
- Ošetrenie prázdnych alebo neplatných polí  

---

## 🧱 Štruktúra projektu
src/
└── main/
├── java/sk/patrikscerba/
│ ├── data/ → Validácia a práca s XML (čítanie, zápis)
│ ├── model/ → Trieda Klient (údaje o klientovi)
│ └── ui/ → Swing GUI – okná aplikácie (Registrácia, Vyhľadávanie, Detail, Zoznam)
└── resources/
└── data/ → klienti.xml (úložisko dát)


---

## ⚙️ Použité technológie

- ☕ **Java 23**
- 💻 **Java Swing (GUI)**
- 💾 **XML perzistencia dát**
- 🎨 **FlatLaf** – moderný vzhľad GUI
- 🧰 **IntelliJ IDEA**
- 🌐 **Git + GitHub**

---

## 🚀 Spustenie aplikácie

Inštrukcie na spustenie budú doplnené v ďalšej verzii (po úprave spúšťania aplikácie).  
Momentálne projekt funguje ako plnohodnotná desktopová Java aplikácia v prostredí **IntelliJ IDEA**,  
kde je možné spúšťať ho priamo cez triedu `EvidenciaApp`.

---

## 🧪 Testovanie a kvalita kódu

Projekt bol otestovaný ako **desktopová CRUD aplikácia**:
- overené čítanie a zápis XML,  
- testované scenáre: registrácia, úprava, mazanie, vyhľadávanie,  
- validácia vstupov pre všetky polia,  
- funkčné Swing GUI s oddelenou logikou a dátovou vrstvou.  

---

## 📦 Verzia

**Verzia 1.0.0 Beta – stabilná verzia (2025)**  
> Základná desktopová verzia bez databázy.  
> Plánované rozšírenie: MySQL + Spring Boot REST API.  

---

---

## 📸 Ukážky aplikácie

Vizualizácia hlavných častí desktopovej aplikácie **Evidencia Fitnes Klientov**:

### 🏠 Hlavné menu
![Hlavné menu](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20191735.png)


### 🧍‍♂️ Registrácia klienta
![Registrácia klienta](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20191802.png)


### 🔍 Vyhľadávanie klienta
![Vyhľadávanie klienta](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20191822.png)


### 📋 Zoznam všetkých klientov
![Zoznam všetkých klientov](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20191843.png)


### ⚠️ Validácia vstupov
![Detail klienta](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20194546.png)


### ℹ️ Detail klienta
![Neplatný formát e-mailu](screenshots/Sn%C3%ADmka%20obrazovky%202025-10-27%20195214.png)

---

> 💡 Obrázky slúžia ako demonštrácia funkčnosti GUI.  
> Ide o desktopovú Java Swing aplikáciu s validáciou vstupov a perzistenciou dát do XML súboru.



## 👨‍💻 Autor

**Patrik Ščerba**  
📍 Slovensko  
🎯 Cieľ: Stať sa Java Developerom  

---

## 🏆 Cieľ portfólia

Tento projekt je súčasťou môjho osobného portfólia.  
Cieľom bolo ukázať zvládnutie:
- **Java Swing GUI**,  
- **práce so súbormi (XML)**,  
- **validácie vstupov**,  
- a **verziovania projektu pomocou Git a GitHub**.  

---

💡 *Tento projekt je prvou stabilnou beta verziou aplikácie. Ďalším krokom bude databázová implementácia (MySQL) a webové rozhranie pomocou Spring Boot a React.*

---

## ⚖️ Licencia

Projekt **Evidencia Fitnes Klientov** je určený na študijné a nekomerčné účely.  
Kód je možné použiť na vzdelávacie účely s uvedením autora.  
© 2025 Patrik Ščerba – všetky práva vyhradené.

---

---

## 💾 Inštalácia a spustenie aplikácie (.exe)

Aplikácia **Evidencia Fitnes Klientov** je dostupná aj ako spustiteľný súbor (.exe) pre operačný systém **Windows**.  
Tento súbor umožňuje používateľovi spustiť aplikáciu **bez potreby vývojového prostredia**.

### 🔹 Spustenie
1. Stiahnite súbor **`EvidenciaFitnesKlientov.exe`** z repozitára.  
2. Uložte ho napríklad na pracovnú plochu.  
3. Spustite ho **dvojklikom** – aplikácia sa automaticky otvorí.

### ⚠️ Poznámka
> Spustiteľný súbor je určený len pre **študijné a prezentačné účely**.  
> Je vytvorený pomocou **Launch4j** a vyžaduje nainštalované prostredie **Java Runtime Environment (JRE 17+)**.








