# Prompt: solve-open-github-issues
# Version: 1.0.0
# Author: Lisa Ortner
# Last Updated: 2025-10-31
# Description: Automatisiert die komplette Issue-Bearbeitung mit Fork-Support
# Parameters: [ORGANISATION], [REPOSITORY]
# Usage: Ersetze [ORGANISATION] und [REPOSITORY] mit deinen Werten
# Example: Gepardec/intellij-maven-jboss-workshop

Automatisiere die Issue-Bearbeitung für das Repository [ORGANISATION]/[REPOSITORY]:

WICHTIG: Ignoriere alle bereits existierenden Pull Requests für die Issues. Erstelle IMMER deine eigene Lösung und einen eigenen Pull Request, unabhängig davon, ob andere bereits PRs erstellt haben.

Für jedes offene Issue:
a. Erstelle einen neuen Branch: fix/issue-{nummer}-{kurzbeschreibung}
b. Analysiere das Issue und seine Anforderungen (OHNE bestehende PRs zu berücksichtigen)
c. Implementiere die notwendigen Änderungen direkt im aktuellen Repository
d. Führe alle relevanten Tests aus
e. Committe die Änderungen mit einer aussagekräftigen Nachricht

Format:
fix: {beschreibung}

Fixes #{nummer}

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>

f. Pushe den Branch zum Remote Repository:
   - Prüfe zuerst, ob du Schreibrechte auf [ORGANISATION]/[REPOSITORY] hast
   - Falls NEIN: Erstelle automatisch einen Fork des Repositories und pushe zum Fork
   - Falls JA: Pushe direkt zum Original-Repository

g. Erstelle IMMER einen Pull Request mit:

Titel: Fix #{nummer}: {issue-titel}

Beschreibung:
## Summary
{Detaillierte Auflistung der Änderungen}

## Test plan
{Beschreibung wie getestet wurde}

Fixes #{nummer}

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Gib eine Zusammenfassung aller bearbeiteten Issues und erstellten Pull Requests aus.