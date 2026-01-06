# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

FAMILIAR (FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) is a domain-specific language for importing, exporting, composing, decomposing, editing, configuring, computing diffs, refactoring, reverse engineering, testing, and reasoning about feature models.

Built with Xtext 2.30.0 (Eclipse language engineering framework) on Java 11 with Maven/Tycho 4.0.4.

## Build Commands

```bash
# Build all modules (from familiar.root directory)
# Note: MAVEN_OPTS required for Java 11+ XML processing
cd familiar.root
MAVEN_OPTS="-Djdk.xml.maxGeneralEntitySizeLimit=0 -Djdk.xml.totalEntitySizeLimit=0" mvn install -DskipTests

# Build executable standalone JAR (from familiar.standalone directory)
cd familiar.standalone && mvn package
# Output: target/*-jar-with-dependencies.jar
```

## Running Tests

```bash
# Run all integration tests (requires full build first)
cd familiar.test
MAVEN_OPTS="-Djdk.xml.maxGeneralEntitySizeLimit=0 -Djdk.xml.totalEntitySizeLimit=0" mvn integration-test

# Run from root (builds + tests)
cd familiar.root
MAVEN_OPTS="-Djdk.xml.maxGeneralEntitySizeLimit=0 -Djdk.xml.totalEntitySizeLimit=0" mvn integration-test
```

**Test Results Notes:**
- Tests run as Eclipse/Tycho plugin tests in an OSGi runtime
- ~958 tests total, ~675 typically pass
- Some tests require optional libraries (TVL, JavaBDD) and are skipped/fail if unavailable
- SAT4J version mismatches may cause `IncompatibleClassChange` errors in some tests
- Test reports: `familiar.test/target/surefire-reports/`

## Running FAMILIAR

**Requires Java 11+**

```bash
# Recommended: use the launcher script (handles Java 11 flags)
./run-familiar.sh                    # Interactive shell
./run-familiar.sh script.fml         # Execute a script

# Or run directly with --add-opens flag (required for Java 11+)
java --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
     -jar familiar.standalone/target/*-jar-with-dependencies.jar

# With additional options
java --add-opens java.base/java.lang.reflect=ALL-UNNAMED \
     -jar <jar> -v --path /path1,/path2 -o /output script.fml
```

**CLI Options:**
- `-v, --verbose`: Enable verbose output
- `-p, --path`: Comma-separated search paths for FML files
- `-o, --output`: Output folder for generated files
- `--version`: Display version (currently 1.2 beta)

## Architecture

### Core Modules

- **FAMILIAR/**: Core interpreter and Java API (~445 Java files)
  - `fr.familiar.interpreter.FMLShell`: Main REPL shell entry point
  - `fr.familiar.parser.*`: AST traversal and command interpretation
  - `fr.familiar.operations.*`: Feature model operations (merge, slice, synthesis, etc.)
  - `fr.familiar.variable.*`: Variable types (feature models, configurations, constraints)
  - `fr.familiar.fm.*`: Feature model abstractions and transformations

- **org.xtext.example.fml.parent/**: Xtext grammar and Eclipse tooling
  - `org.xtext.example.fml/src/.../Fml.xtext`: Grammar definition (671 lines)
  - IDE, UI, web, and test subprojects

- **familiar.test/**: Integration tests with example FML scripts

- **FML-gettingstarted/**: Example Maven project for using FAMILIAR as a Java library

### Key Entry Points

- Standalone CLI: `fr.familiar.standalone.FML` (FAMILIAR/src/main/java/)
- Shell/Interpreter: `fr.familiar.interpreter.FMLShell`
- Grammar: `org.xtext.example.fml/src/org/xtext/example/mydsl/Fml.xtext`

### Third-Party Integrations

- FeatureIDE: Feature model IDE integration
- SPLAR: SAT-based reasoning (from SPLOT)
- SAT4J: SAT solver

## FML Language Basics

```fml
// Define a feature model
fm1 = FM (Root: A B [C]; A: (D|E)+; C -> A;)

// Operations
fm2 = merge intersection {fm1 fm3}
s = configs fm1           // count configurations
valid = isValid fm1       // check validity
feats = features fm1      // get all features
```

Key operations: `merge`, `slice`, `aggregate`, `synthesis`, `configs`, `isValid`, `features`, `children`, `parent`, `convert`

## Docker

```bash
docker pull familiarlang/familiar:1.2
docker run -v $PWD:/familiar/host -it familiarlang/familiar:1.2
```

## Resources

- Online IDE: http://familiar.variability.io/ide/familiar
- Tutorials: https://familiar-project.github.io/
- Project site: http://familiar-project.github.com/
