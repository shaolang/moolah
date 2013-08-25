/* Copyright 2013 Shaolang Ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.ackage com.github.moolah;
 */
package com.github.moolah;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ValueObjectContractMatcher<T> extends BaseMatcher<T> {
    public ValueObjectContractMatcher(T identicalInstance, T differentInstance) {
        this.same = identicalInstance;
        this.different = differentInstance;
    }

    public boolean matches(Object actual) {
        notEqualsNull = !actual.equals(null);
        notEqualsArbitraryObject = !actual.equals(new Object());
        equalsSame = same.equals(actual);
        reflexiveEqualsSame = actual.equals(same);
        notReflexiveEqualsDifferent = !actual.equals(different);
        notEqualsDifferent = !different.equals(actual);
        equalsSameHashCode = same.hashCode() == actual.hashCode();
        notEqualsDifferentHashCode = different.hashCode() != actual.hashCode();

        return notEqualsNull && notEqualsArbitraryObject
            && equalsSame && notEqualsDifferent
            && reflexiveEqualsSame && notReflexiveEqualsDifferent
            && equalsSameHashCode && notEqualsDifferentHashCode;
    }

    public void describeMismatch(Object actual, Description description) {
        if (!notEqualsNull) {
            description.appendText("should not be equal to null ");
        } else if (!notEqualsArbitraryObject) {
            description.appendText(
                    "should not be equal to any arbitrary object");
        } else if (!equalsSame) {
            description.appendText("to be equal to ")
                .appendValue(same);
        } else if (!notEqualsDifferent) {
            description.appendText("not to be equal to ")
                .appendValue(different);
        } else if (!reflexiveEqualsSame) {
            description.appendText("should reflexively be equal to ");
        } else if (!notReflexiveEqualsDifferent) {
            description.appendText("should not reflexively be equal to ");
        } else if (!equalsSameHashCode) {
            description.appendText("to have the same hash code as ")
                .appendValue(same);
        } else {
            description.appendText("to have a different hash code from ")
                .appendValue(different);
        }
    }

    public void describeTo(Description description) {
        if (!equalsSame || !equalsSameHashCode || !notEqualsNull
                || !reflexiveEqualsSame) {
            description.appendValue(same);
        } else if (!notEqualsDifferent || !notEqualsDifferentHashCode
                || !notReflexiveEqualsDifferent) {
            description.appendValue(different);
        }
    }

    private final T same;
    private final T different;
    private boolean notEqualsNull = false;
    private boolean notEqualsArbitraryObject = false;
    private boolean equalsSame = false;
    private boolean notEqualsDifferent = false;
    private boolean reflexiveEqualsSame = false;
    private boolean notReflexiveEqualsDifferent = false;
    private boolean equalsSameHashCode = false;
    private boolean notEqualsDifferentHashCode = false;
}
