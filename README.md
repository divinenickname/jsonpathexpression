[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
![main branch build](https://github.com/divinenickname/utgen-kotlin-core/actions/workflows/mainbranch-build.yml/badge.svg)
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https://repo1.maven.org/maven2/org/ilinykh/kotlin/logic-json-path/logic-json-path/maven-metadata.xml&style=flat&label=sonatype-central&color=green)
![Coverage](.github/badges/jacoco.svg)

# Logic JsonPath
This library extends [JsonPath](https://github.com/json-path/JsonPath) for direct logical operations on JSON data.
It allows complex querying and filtering within JSON structures using an extended syntax with logical operators.

## Getting Started
Library is available in the [Central Maven Repository](https://central.sonatype.com/artifact/org.ilinykh.kotlin.logic-json-path/logic-json-path).
You can find the latest version under the "sonatype-central" badge at the top of the README.

### Gradle kotlin dsl
``` kotlin
implementation("org.ilinykh.kotlin.logic-json-path:logic-json-path:1.0.0")
```

### Gradle
```groovy
implementation 'org.ilinykh.kotlin.logic-json-path:logic-json-path:1.0.0'
```

### Maven
```xml
<dependency>
    <groupId>org.ilinykh.kotlin.logic-json-path</groupId>
    <artifactId>logic-json-path</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Operators
| Operator | Description                                                         | Constraints                                                              |
|----------|---------------------------------------------------------------------|--------------------------------------------------------------------------|
| =        | *Equals*. <br/>Left is equal to right                               | Boolean types works case insensitive, otherwise works as string compare. |
| !=       | *Not Equals*. <br/>Left is NOT equal to right.                      | Boolean types works case insensitive, otherwise works as string compare. |
| &        | *AND*                                                               | Works only with boolean.                                                 |
| \|       | *OR*                                                                | Works only with boolean.                                                 |
| <        | *Less Than* <br/>Left element is less to right.                     | Works only with numbers.                                                 |
| <=       | *Less Or Equal*. <br/>Left element is less or equal to right.       | Works only with numbers.                                                 |
| \>       | *Greater Than*. <br/>Left element is greater to right.              | Works only with numbers.                                                 |
| \>=      | *Greater Or Equal*. <br/>Left element is greater or equal to right. | Works only with numbers.                                                 |

⚠️ Mathematics operations like `+`, `-`, `/`, `*` are not supported yet.

## Expression language
Expressions must be declared in reverse polish notation ([RPN](https://en.wikipedia.org/wiki/Reverse_Polish_notation)).
Separate each element with a `#` symbol.

**Correct**: `#10#11#>#12#13#<#=` in infix form this expression equivalent is `(10>11)=(12<13)`  
**Incorrect**: `#10#11#>#12#13#>`. You can't use comparison operations on the boolean type.

## Examples
### Simple example
Let's write expression for JSON below.
```json
{
  "payload": {
    "first": {
      "value": true
    },
    "second": {
      "value": false
    }
  }
}
```

**Task**: compare two boolean fields and return true if they're same and false if not.  
**Expression**: `"#$.payload.first.value#$.payload.second.value#="`  
**Result**: `false`

Explanation:
We can tokenize this expression:
1. `$.payload.first.value` - first element (json-path)
2. `$.payload.second.value` - second element (json-path)
3. `=` - operation under elements

After replacing to values token stack looks like:
1. true
2. false
3. =

After making operation over stack we get result `false`

### Multiple json-path expressions
You also can make huge expressions with multiple elements. In this case RPN might me slight difficult to understand.

```json
{
  "payload": {
    "first": {
      "value": true
    },
    "second": {
      "value": false
    },
    "third": {
      "value": true
    }
  }
}
```

**Task**: Return true if three elements are the same; otherwise false.  
**Expression (in infix pseudocode)**: `(payload.first.value = payload.second.value) & (payload.first.value = payload.third.value)`  
**Expression (RPN)**: `#$.payload.first.value#$.payload.second.value#=#$.payload.first.value#$.payload.third.value#=#&`

**Tokens**:
1. `$.payload.first.value` - first element (json-path)
2. `$.payload.second.value` - second element (json-path)
3. `=` - operation under elements
4. `$.payload.first.value` - first element (json-path)
5. `$.payload.third.value` - third element (json-path)
6. `&` - operation under elements

**Result**: `false`

### Json-Path fun expression
You also can use internal [json-path functions](https://github.com/json-path/JsonPath?tab=readme-ov-file#functions).

```json
{
    "store": {
        "book": [
            {
                "title": "Sayings of the Century",
                "price": 8.95
            },
            {
                "title": "Sword of Honour",
                "price": 12.99
            },
            {
                "title": "Moby Dick",
                "price": 8.99
            },
            {
                "title": "Cipollino",
                "price": 22.99
            }
        ]
    }
}
```
**Task**: calculate price and return true if price sum is less than 100.  
**Expression**: `#$.sum($.store.book[*].price)#100#<`

**Tokens**:
1. `$.sum($.store.book[*].price)` - first element (json-path function)
2. `100` - second element
3`<` - operation under elements

**Result**: `true`

**Explanation**: 
- `$.store.book[*].price` maps json into array `[8.95, 12.99, 8.99, 22.99]`.
- `$.sum($.store.book[*].price)` - sum array and return value `53,92`
- `100<` - compare previous value `53,92` with `100`. In infix form this is equivalent for `53,92 < 100`
- Return `true`
