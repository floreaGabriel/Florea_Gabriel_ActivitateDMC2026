# Laborator 3

## Pe scurt (ce reții pentru examen)

- **Ciclu de viață al activității**: `onCreate` → `onStart` → `onResume` (vizibil) → apoi `onPause` / `onStop` când pleci din ecran sau ecran e acoperit.
- **Logcat**: `Log.d`, `Log.e`, `Log.w`, `Log.i`, `Log.v` + filtru pe tag.
- **Intent**: deschizi alt ecran (`startActivity` / `startActivityForResult`).
- **Date între ecrane**: `Bundle` / `Intent.putExtra`, în celălalt: `getIntent().getExtras()` / `getStringExtra` etc.
- **Înapoi spre ecranul anterior**: `setResult(RESULT_OK, intent)` + `finish()`; în prima activitate: `onActivityResult`.

**Rădăcină proiect:** `laborator2/app/src/main/`

---

## Cerințe (original)

1. Continuati in proiectul din laboratorul precedent sau creați unul nou în Android Studio cu API 23 sau API 24. Realizati o activitate noua pe care sa o setati ca activitate implicita.

2. Implementați metodele pentru prezentarea ciclului de viață al unei activități: onStart(), onResume(), onPause(), onStop().

3. In cadrul fiecărei metode salvați un log de tipul error, warning, debug, info: Log.e(), Log.w(), Log.d(), Log.i(), Log.v().

4. Filtrați si căutați logurile salvate în tab-ul Logat.

5. Adăugați o nouă activitate în cadrul proiectului. Setați din AndroidManifest ca noua activitate să fie lansată la deschiderea aplicației.

6. Adăugați o a treia activitate în cadrul proiectului.

7. În cea de a doua activitate adăugați un Button. La apăsarea butonului respectiv deschideți cea de a treia activitate printr-un Intent.

8. La deschiderea activității trimiteți un mesaj și două valori de tip întreg către activitatea deschisă printr-un Bundle.

9. În activitatea nou deschisă preluați informațiile trimise din activitatea precedentă și le afișați într-un Toast.

10. În activitatea nou deschisa trebuie sa aveți un buton care să trimită către activitatea precedentă un alt mesaj si suma celor două valori primite.

11. În activitatea inițială vor fi afișate printr-un Toast mesajul primit și valoarea calculată.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| `onStart`, `onResume`, `onPause`, `onStop` + `Log.*` | `app/src/main/java/.../MainActivity.java` |
| `Intent` nou ecran, `putExtras` / `Bundle` | `.../MainActivity2.java` (buton → `MainActivity3`) |
| `getIntent().getExtras()`, `getString` / `getInt` | `.../MainActivity3.java` |
| `setResult`, `putExtra` înapoi, `finish` | `.../MainActivity3.java` |
| `onActivityResult`, `Toast` cu ce s-a primit | `.../MainActivity2.java` |
| Activități înregistrate, care e **launcher** | `app/src/main/AndroidManifest.xml` |
| Layout-uri a 2-a / 3-a activitate | `res/layout/activity_main2.xml`, `res/layout/activity_main3.xml` |

**Notă proiect:** cerința 5 (a doua = launcher) nu mai e așa — în manifest **launcher** e `ActivitateLab4`. Fluxul L3 (2→3 cu rezultat) e totuși valabil în `MainActivity2` + `MainActivity3`.

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 1, 5, 6 | Activități, launcher | `AndroidManifest.xml` |
| 2, 3, 4 | Lifecycle + loguri | `MainActivity.java` |
| 7 | Buton + `Intent` | `MainActivity2.java` + `activity_main2.xml` |
| 8 | `Bundle` trimis | `MainActivity2.java` (`putExtras` pe `Intent`) |
| 9 | Citit extras + `Toast` | `MainActivity3.java` |
| 10 | Rezultat spre ecran 2 | `MainActivity3.java` (`setResult` + `finish`) |
| 11 | Rezultat primit, `Toast` | `MainActivity2.java` (`onActivityResult`) |

---

## Pași (rezolvare)

1. 3 `Activity` + înregistrare în `AndroidManifest` (o activitate = `<activity>`).
2. Schimbat `<intent-filter>` (MAIN) pe activitatea care trebuie pornită la cold start.
3. În a doua: `new Intent` + `putExtras` / `putExtra` + `startActivityForResult` (sau contract nou API).
4. În a treia: `getIntent()` → `Toast`; buton: `setResult(RESULT_OK, intent)` + `finish()`.
5. În a doua: override `onActivityResult` — citești `data.getStringExtra` / `getIntExtra` → `Toast`.
