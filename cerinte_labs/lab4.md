# Laborator 4

## Pe scurt (ce reții pentru examen)

- **Clasă model (domeniu)**: câmpuri (string, int, boolean, **enum** pentru mulțimi finite), get/set, eventual `toString()`.
- **Formular** (`Activity` + layout): `EditText`, `CheckBox`, `RadioButton`, `Spinner`/`RatingBar`/`Switch`/`ToggleButton` (cerința cere varietate).
- **Comunicare între activități**: `startActivityForResult` → a doua activitate construiește obiectul → `setResult` cu **`Intent` + `putExtra`**; date complexe: **fie câmp cu câmp, fie `Parcelable`** (L6 pune `Parcelable` clar).
- **Afișare obiect** în ecranul principal: `TextView` + valori luate din `onActivityResult` / `getParcelableExtra`.

**Rădăcină proiect:** `laborator2/app/src/main/`

**Model în proiect:** clasa `GFMagazin` (GF = inițiale) — magazin cu tip `enum` etc.

---

## Cerințe (original)

1. Continuati proiectul din laboratorul precedent sau creați unul nou în Android Studio cu API 23 sau API 24. Realizati o activitate noua pe care sa o setati ca activitate implicita.

2. Creați o clasă pentru un obiect din realitate care conține inițialele numelui. Exemplu Alin Zamfiroiu – clasa Magazin (contine ambele initiale). Această clasă trebuie să conțină minim cinci atribute de tipuri diferite, dintre care minim unul să fie string, unul de tipul boolean, unul de tip întreg și un atribut sa aiba valori posibile într-o mulțime finită (enum).

3. Adăugați o nouă activitate în cadrul proiectului pentru preluarea de date pentru crearea de obiecte de tipul clasei create anterior. Activitatea trebuie să conțină view-uri de tipul: TextView, EditText, Button, CheckBox, RadioButton, Spinner, RattingBar, Switch, ToggleButton. Această activitate de introducere date este deschisă din prima activitate printr-un buton de adăugare printr-un intenet dependent.

4. Datele introduse de utilizator sunt folosite pentru crearea unei instanțe a clasei. Această instanță este returnată către prima activitate, din cadrul căreia a fost deschisă activitatea de introducere date. Obiectul este transmis prin bundle (poate fi folosit si Parcelable sau trimiteți fiecare atribut in parte). În activitatea principală este afișat obiectul prin intermediul unor view-uri de tip TextView.

5. Utilizatii Inteligenta Artificiala pentru a imbunatatii modul de aranjare al view-urilur in cadrul activitatii de preluare de date sau de adaugare a unui nou obiect. Puteti transmite catre AI, layoutul si sa ii solicitati sa il imbunatateasca insa trebuie sa pastreze id-urile view-urilor. Astfel functionalitatea implementata de voi se pastreaza si doar modul de prezentare o sa fie diferit.

---

## Index rapid (exam) — „mă duc direct la …”

| Cauți / te întreabă despre… | Deschide (în `laborator2/`) |
|----------------------------|----------------------------|
| Clasă model, **enum**, câmpuri, `toString` | `app/src/main/java/.../GFMagazin.java` |
| **Parcelable** (și în L4 poți, în proiect e folosit la L5–L6) | `GFMagazin.java` (`Parcel`, `CREATOR`, `writeToParcel`) |
| Formular, citire controale, `setResult` | `.../PrelucrareDateLab4Activity.java` |
| Layout formular (controale + id-uri) | `res/layout/activity_prelucrare_date_lab4.xml` |
| Ecran listă + buton „adaugă” + `onActivityResult` | `.../ActivitateLab4.java` |
| Layout principal (listă, butoane) | `res/layout/activity_activitate_lab4.xml` |
| Manifest (launcher) | `AndroidManifest.xml` — `ActivitateLab4` |

---

## Mapare cerințe → fișiere

| # | Subiect | Unde e în proiect |
|---|---------|--------------------|
| 1 | Activitate start | `ActivitateLab4.java` + `AndroidManifest.xml` |
| 2 | Model + enum | `GFMagazin.java` (`TipMagazin` etc.) |
| 3 | Formular, multe view-uri, deschidere cu `Intent` | `PrelucrareDateLab4Activity.java` + `activity_prelucrare_date_lab4.xml` |
| 4 | Return obiect, primire în `onActivityResult` | `PrelucrareDateLab4Activity.java` (`setResult`, `putExtra`); `ActivitateLab4.java` |
| 5 | Layout îmbunătățit | `activity_prelucrare_date_lab4.xml` (păstrat id-uri) |

---

## Pași (rezolvare)

1. Definești clasa (atribute + **enum** + constructor).
2. Layout formular: un `id` clar pe fiecare control; legi în `Activity` (`findViewById`).
3. Din principal: `startActivityForResult` către formular; la salvare: `Intent` cu extras / obiect `Parcelable` + `setResult(RESULT_OK)`.
4. În principal: `onActivityResult` — citești extras, actualizezi `TextView` / listă (în varianta ta completă, merge spre L5).
