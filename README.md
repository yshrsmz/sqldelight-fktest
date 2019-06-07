sqldelight Foreign Keys test
===

https://github.com/square/sqldelight/issues/1356

On iOS foreign_keys seems to be not working if statement is wrapped with transaction.

### Run Test

Schema is [here](https://github.com/yshrsmz/sqldelight-fktest/tree/master/app/src/commonMain/sqldelight/com/codingfeline/fktest)

Test case is [here](https://github.com/yshrsmz/sqldelight-fktest/blob/master/app/src/commonTest/kotlin/com/codingfeline/fktest/FkTest.kt)

```
$ ./gradlew testDebugUnitTest
$ ./gradlew iosTest
```

iOS test fails with following error:

```
> Task :app:iosTest FAILED
[==========] Running 4 tests from 1 test cases.
[----------] Global test environment set-up.
[----------] 4 tests from com.codingfeline.fktest.FkTest
[ RUN      ] com.codingfeline.fktest.FkTest.should succeed if FK is disabled
[       OK ] com.codingfeline.fktest.FkTest.should succeed if FK is disabled (84 ms)
[ RUN      ] com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied
kotlin.Exception: android/database/sqlite/SQLiteConstraintException - FOREIGN KEY constraint failed (code 787)
[       OK ] com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied (8 ms)
[ RUN      ] com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied - transaction
books count after insertion attempt: 1
kotlin.AssertionError: should throw exception
Invalid connection: com.apple.coresymbolicationd
        at 0   test.kexe                           0x000000010fc89296 kfun:kotlin.Error.<init>(kotlin.String?)kotlin.Error + 70 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/main/kotlin/kotlin/Exceptions.kt:12:5)
        at 1   test.kexe                           0x000000010fc890e7 kfun:kotlin.AssertionError.<init>(kotlin.Any?)kotlin.AssertionError + 119 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/main/kotlin/kotlin/Exceptions.kt:128:5)
        at 2   test.kexe                           0x000000010fd154d9 kfun:kotlin.test.DefaultAsserter.fail(kotlin.String?)kotlin.Nothing + 137 (/Users/teamcity/buildAgent/work/4d622a065c544371/backend.native/build/stdlib/kotlin/test/DefaultAsserter.kt:16:19)
        at 3   test.kexe                           0x000000010fd17af8 kfun:kotlin.test.fail(kotlin.String?)kotlin.Nothing + 104 (/Users/teamcity/buildAgent/work/4d622a065c544371/backend.native/build/stdlib/kotlin/test/Assertions.kt:<unknown>)
        at 4   test.kexe                           0x000000010fd178bc kfun:com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied - transaction() + 1100 (/Users/a12897/repos/test/foreignkeys-test/app/src/commonTest/kotlin/com/codingfeline/fktest/FkTest.kt:<unknown>)
        at 5   test.kexe                           0x000000010fd17433 kfun:com.codingfeline.fktest.$FkTest$test$0.$should fail as FK constraint is not satisfied - transaction$FUNCTION_REFERENCE$5.invoke#internal + 67 (/Users/a12897/repos/test/foreignkeys-test/app/src/commonTest/kotlin/com/codingfeline/fktest/FkTest.kt:<unknown>)
        at 6   test.kexe                           0x000000010fd1739b kfun:com.codingfeline.fktest.$FkTest$test$0.$should fail as FK constraint is not satisfied - transaction$FUNCTION_REFERENCE$5.$<bridge-UNNN>invoke(#GENERIC)#internal + 75 (/Users/a12897/repos/test/foreignkeys-test/app/src/commonTest/kotlin/com/codingfeline/fktest/FkTest.kt:99:6)
        at 7   test.kexe                           0x000000010fd48f2c kfun:kotlin.native.internal.test.BaseClassSuite.TestCase.run() + 492 (/Users/a12897/repos/test/foreignkeys-test/no source file:1:1)
        at 8   test.kexe                           0x000000010fc9911b kfun:kotlin.native.internal.test.TestRunner.run#internal + 1131 (/Users/a12897/repos/test/foreignkeys-test/no source file:1:1)
        at 9   test.kexe                           0x000000010fc986f3 kfun:kotlin.native.internal.test.TestRunner.runIteration#internal + 1395 (/Users/a12897/repos/test/foreignkeys-test/no source file:1:1)
        at 10  test.kexe                           0x000000010fc97ec0 kfun:kotlin.native.internal.test.TestRunner.run()ValueType + 608 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/main/kotlin/kotlin/native/internal/test/TestRunner.kt:232:17)
        at 11  test.kexe                           0x000000010fc96fce kfun:kotlin.native.internal.test.testLauncherEntryPoint(kotlin.Array<kotlin.String>)ValueType + 110 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/main/kotlin/kotlin/native/internal/test/Launcher.kt:19:47)
        at 12  test.kexe                           0x000000010fc96f32 kfun:kotlin.native.internal.test.main(kotlin.Array<kotlin.String>) + 50 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/main/kotlin/kotlin/native/internal/test/Launcher.kt:23:5)
        at 13  test.kexe                           0x000000010fc96e87 Konan_start + 71 (/Users/teamcity/buildAgent/work/4d622a065c544371/runtime/src/launcher/kotlin/konan/start.kt:<unknown>)
        at 14  test.kexe                           0x000000010fc96e01 Konan_run_start + 113
        at 15  test.kexe                           0x000000010fc96d7b Konan_main + 27
        at 16  libdyld.dylib                       0x000000011195e541 start + 1
[  FAILED  ] com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied - transaction (47 ms)
[ RUN      ] com.codingfeline.fktest.FkTest.should succeed if FK constraint is satisfied
[       OK ] com.codingfeline.fktest.FkTest.should succeed if FK constraint is satisfied (50 ms)
[----------] 4 tests from com.codingfeline.fktest.FkTest (217 ms total)

[----------] Global test environment tear-down
[==========] 4 tests from 1 test cases ran. (217 ms total)
[  PASSED  ] 3 tests.
[  FAILED  ] 1 tests, listed below:
[  FAILED  ] com.codingfeline.fktest.FkTest.should fail as FK constraint is not satisfied - transaction

1 FAILED TESTS
```
