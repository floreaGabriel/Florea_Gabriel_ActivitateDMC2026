# Laborator 2

## Pe scurt (ce reții pentru examen)

- **Proiect Android (Java)**: modulul `app` + resurse; **layout XML** (views + constrângeri).
- **Resurse** (`strings.xml`): texte scoase din layout — ușor de tradus.
- **i18n**: foldere `values`, `values-ro-rRO`, etc. — același `name` al string-ului, conținut diferit.
- **Debug**: `onCreate` = primul loc sigur în care pui breakpoint.

**Rădăcină proiect:** `laborator2/app/src/main/`

---

## Cerințe (original)

1. Continuati lucrul in aplicatia realizata in laboratorul precedent sau creați o noua aplicație în Android Studio cu o activitate Empty Views, în java, cu API 23 / API 24.

2. Rulați aplicația pe emulator sau pe dispozitivul mobil.

3. Identificați toate fișierele din cadrul proiectului.

4. Rulați aplicația în modul debug, punând un breakpoint în metoda onCreate(). Rulați pas cu pas.

5. In fișierul activity_main.xml, adăugați un buton si stabiliți constrângerile, astfel încât să fie deasupra TextView-ului existent deja. Rulați iar aplicația.

6. În fișierul activity_main.xml, adăugați un EditText (Plain Text) și stabiliți constrângerile, astfel încât să fie poziționat sub TextView-ul existent.

7. Folosiți fișierul de resurse pentru a seta textele afișate în cele trei view-uri (controale). Rulați iar aplicația.

8. (Optional) Cand apasam pe buton textul din View-ul de tip textView sa se modifice cu un alt text la alegere.

9. Internationalizarea aplicatiei: adaugati cel putin alte doua limbi straine pentru textele utilizate in cadrul aplicatiei dezvoltate. Schimbati limba de utilizare a emulaorului, astfel incat sa prezentati trecerea de la o limba la alta in cadrul aplicatiei.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| `ConstraintLayout`, buton deasupra, EditText jos | `app/src/main/res/layout/activity_main.xml` |
| `minSdk` / compilație | `app/build.gradle.kts` (sau `app/build.gradle`) |
| Ecran pornit = care activitate | `app/src/main/AndroidManifest.xml` ( `<intent-filter>` `MAIN` / `LAUNCHER` ) |
| Texte `@string/...`, traduceri | `res/values/strings.xml`, `res/values-ro-rRO/strings.xml` |
| Debug `onCreate` | `app/src/main/java/com/florea_gabriel/labs/MainActivity.java` |
| (Opțional) click + `setText` | clasa care face `setContentView(R.layout.activity_main)` + `strings.xml` |

**Notă:** `activity_main.xml` există, dar `MainActivity` folosește acum `activity_main2` — pentru proba de lab, legătura e tot `setContentView(R.layout.activity_main)` când e nevoie de ecranul clasic L2.

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 1 | Proiect / API | `app/build.gradle.kts`, `settings.gradle.kts` |
| 2–3 | Rulare / structură | Proiectul în Android Studio; `java/`, `res/`, `AndroidManifest.xml` |
| 4 | Breakpoint | Orice `Activity` — `onCreate` (ex. `MainActivity.java`) |
| 5–6 | UI | `res/layout/activity_main.xml` |
| 7 | `strings` + legătură în layout | `res/values/strings.xml` + `activity_main.xml` |
| 8 | Handler click | `Activity` + `findViewById` + `strings.xml` |
| 9 | Alte limbi | `res/values*/strings.xml` (ex. `values-ro-rRO/`) |

---

## Pași (rezolvare) — fără comentarii inutile

1. Proiect Java, API 23+; `AndroidManifest` — activitatea de start.  
2. `activity_main.xml` — buton (deasupra textului) + `EditText` (sub) — doar prin `ConstraintLayout`.  
3. `strings.xml` — toate `android:text="@string/..."` în layout.  
4. Rulat + Logcat: nu e neapărat aici, dar L4+ folosește `Log` în alte activități.  
5. (Opțional) `setOnClickListener` → `textView.setText(R.string.…)`  
6. i18n — duplicate `string name="..."` în alte `values-XX/`.
