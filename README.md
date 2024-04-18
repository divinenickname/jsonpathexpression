[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
![main branch build](https://github.com/divinenickname/utgen-kotlin-core/actions/workflows/mainbranch-build.yml/badge.svg)
![Coverage](.github/badges/jacoco.svg)

# Logic JsonPath
This library extends [JsonPath](https://github.com/json-path/JsonPath) to allow direct logical operations on JSON data. 
It enables users to perform complex querying and filtering directly within JSON structures using an extended syntax 
that supports logical operators.

## Operators
| Operator | Description                                                               | Constraints                                                              |
|----------|---------------------------------------------------------------------------|--------------------------------------------------------------------------|
| =        | *Equals*. <br/>Left is equal to right                                     | Boolean types works case insensitive, otherwise works as string compare. |
| !=       | *Not Equals*. <br/>Left is NOT equal to right.                            | Same as equals.                                                          |
| &        | *AND*                                                                     | Works only with numbers.                                                 |
| \|       | *OR*                                                                      | Works only with numbers.                                                 |
| <        | *Less than* <br/>Left element is less to right.                           | Works only with numbers.                                                 |
| <=       | *Less Or Equals than*. <br/>Left element is less or equal to right.       | Works only with numbers.                                                 |
| \>       | *Greater than*. <br/>Left element is greater to right.                    | Works only with numbers.                                                 |
| \>=      | *Greater Or Equals than*. <br/>Left element is greater or equal to right. | Works only with numbers.                                                 |

## Expression language
Expression language is simple. You must declare expressions using reverse polish notation 
([RPN](https://en.wikipedia.org/wiki/Reverse_Polish_notation)). Each element must be separated using `$` symbol.

### Examples
#### Simple example
Task: compare two boolean fields and return true if they're same and false if not.

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

Expression: `"#$.payload.first.value#$.payload.second.value#="`  
Result: `false`

We can tokenize this expression:
1. `$.payload.first.value` - first element (json-path)
2. `$.payload.second.value` - second element (json-path)
3. `=` - operation under elements

After replacing to values token stack looks like:
1. true
2. false
3. =

After making operation over stack we get result `false`

#### Multiple json-path expressions
You also can make huge expressions with multiple elements. In this case RPN might me slight difficult to understand.

Task: compare 3 elements in json. Return true if all of them are same.

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
Expression (in pseudocode): `(payload.first.value = payload.second.value) & (payload.first.value = payload.third.value)`  
Expression (RPN): `#$.payload.first.value#$.payload.second.value#=#$.payload.first.value#$.payload.third.value#=#&`

Tokens:
1. `$.payload.first.value` - first element (json-path)
2. `$.payload.second.value` - second element (json-path)
3. `=` - operation under elements
4. `$.payload.first.value` - first element (json-path)
5. `$.payload.third.value` - third element (json-path)
6. `&` - operation under elements

Result: `false`
