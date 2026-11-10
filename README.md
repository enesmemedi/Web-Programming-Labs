# Web Programming — Labs (FINKI)

Spring Boot project for the **Веб програмирање** lab series, group **prefix A**.

Each lab continues the previous lab's code rather than starting fresh, so the labs
are kept as **commits on `main`, each tagged `labN`** — not as separate folders.
The git history *is* the progression.

## Labs

| Tag | Lab | What it adds |
|-----|-----|--------------|
| `lab2` | Lab 2 | Spring MVC `@Controller`s taking over from the servlets (servlets kept, moved to `/servlet/*`), `Instructor` model + repository + service, full workshop CRUD, shared `workshop-form.html` |

> Lab 1 (servlets + Thymeleaf) has no tag — Lab 2 was built on top of it in place,
> before this repo had its first commit, so its standalone state no longer exists.

## Opening a specific lab

```bash
git checkout lab2     # the exact state submitted for Lab 2
git checkout main     # back to the latest work
```

## Running

```bash
./mvnw spring-boot:run
```

Then open <http://localhost:9090>. All data is in-memory (`bootstrap/DataHolder`)
and is rebuilt on every start, so edits and registrations do not survive a restart.

Routes:

| Path | What it is |
|------|-----------|
| `/` | redirects to `/workshops` |
| `/workshops` | list + search |
| `/workshops/workshop-form` | add form |
| `/workshops/workshop-form/{id}` | edit form |
| `/servlet/workshops`, `/servlet/registration` | the original Lab 1 servlets, still live |

## Adding the next lab

```bash
git add -A
git commit -m "Lab 3: <what it adds>"
git tag lab3
git push && git push --tags
```
