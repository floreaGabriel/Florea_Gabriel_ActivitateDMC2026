# Laborator 7

## Pe scurt (ce reții pentru examen)

- **Fișier intern** (`openFileOutput` / `openFileInput` sau `File` în `getFilesDir()`): păstrezi stringuri/linii (ex. câte o linie = un obiect serializat simplu: `;` separate).
- **SharedPreferences**: setări mici (dimensiune, culoare) — `getSharedPreferences` + `edit().putX().apply()`.
- **Activitate setări**: utilizator alege; la **pornirea** ecranului care depinde, citești `SharedPreferences` și aplici culoare / `textSize` pe `TextView` etc.
- Fisiere **separate**: de ex. unul „toate obiectele”, altul „favorite” (cerință L7).

**Rădăcină proiect:** `laborator2/app/src/main/`

---

## Cerințe (original)

1. Utilizați proiectul din laboratorul precedent.

2. Modificați activitatea de adăugare de noi obiecte, astfel încât atunci când un nou obiect de tipul clasei voastre este creat, acesta să fie salvat și într-un fișier. Salvarea noului obiect se face prin adăugarea acestuia în fișier.

3. În activitatea principală modificați funcționalitatea de LongItemClick, astfel încât prin acest eveniment să fie salvat obiectul selectat într-un fișier de obiecte favorite. Acest fisier este separat fata de cel initial unde sunt salvate toate.

4. Adăugați o nouă activitate de setări în care utilizatorul își poate seta dimensiunea și culoarea utilizată pentru textele afișate. Aceste setări sunt salvate într-un fișier SharedPreferences. Informațiile salvate în SharedPreferences sunt folosite în activitatea de adăugare obiecte, astfel încât la viitoarele utilizări ale aplicației, activitatea respectivă afișează textele în culoarea aleasă de utilizator și la dimensiunea setată de acesta.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| **Scriere/citire fișier** (`FileOutputStream`, `FileInputStream`, `BufferedReader`) | `.../PrelucrareDateLab4Activity.java` (`salveazaInFisier`, `FILE_NAME`); `.../ActivitateLab4.java` (`incarcaMagazineDinFisier`) |
| Fișier **favorite** (append la long click) | `ActivitateLab4.java` (`salveazaFavoritInFisier`, `FAVORITES_FILE`) |
| **SharedPreferences** chei + salvare | `.../SettingsActivity.java` (`PREFS_NAME`, `KEY_TEXT_SIZE`, `KEY_TEXT_COLOR`, `editor.apply`) |
| Citește setări și le aplică pe text | `.../PrelucrareDateLab4Activity.java` (`aplicaSetariText`, `getSharedPreferences` → `setTextSize`, `setTextColor`) |
| Layout ecran setări | `res/layout/activity_settings.xml` |
| `LongItemClick` (favorite) | `ActivitateLab4.java` (`setOnItemLongClickListener`) |

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 2 | Salvare obiect nou la adăugare | `PrelucrareDateLab4Activity.java` (`salveazaInFisier` → `magazine.txt`); reîncărcare: `ActivitateLab4` — `incarcaMagazineDinFisier` |
| 3 | Long click → favorite | `ActivitateLab4.java` |
| 4 | Setări + prefs + aplicare | `SettingsActivity.java` + `PrelucrareDateLab4Activity.java` |

---

## Pași (rezolvare)

1. Constantă nume fișier; la „Salvare” obiect: `openFileOutput(name, MODE_APPEND)` + linie (format clar pentru parse).  
2. La `onCreate` principal: dacă există fișier, parse linie cu linie → `ArrayList` + `adapter.notifyDataSetChanged()`.  
3. `LongItemClick` → scrii în al doilea fișier (append) o reprezentare a obiectului.  
4. `SettingsActivity`: `EditText` dimensiune + `RadioGroup` culoare → `SharedPreferences.putInt/putString` + `apply()`.  
5. În `onCreate` formular: citești prefs, setezi mărime/culoare pe toate lable-urile relevante.
