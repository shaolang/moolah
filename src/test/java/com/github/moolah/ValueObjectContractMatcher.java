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
    public static <T> ValueObjectContractMatcher<T> adheresToValueObjectContract(
            T identicalInstance, T differentInstance) {
        return new ValueObjectContractMatcher(identicalInstance, differentInstance);
    }

    public ValueObjectContractMatcher(T identicalInstance, T differentInstance) {
        this.same = identicalInstance;
        this.different = differentInstance;
    }

    public boolean matches(Object actual) {
        equalsSame = same.equals(actual);
        notEqualsDifferent = !different.equals(actual);
        equalsSameHashCode = same.hashCode() == actual.hashCode();
        notEqualsDifferentHashCode = different.hashCode() != actual.hashCode();

        return equalsSame && notEqualsDifferent
            && equalsSameHashCode && notEqualsDifferentHashCode;
    }

    public void describeMismatch(Object actual, Description description) {
        if (!equalsSame) {
            description.appendText("to be equal to ")
                .appendValue(same);
        } else if (!notEqualsDifferent) {
            description.appendText("not to be equal to ")
                .appendValue(different);
        } else if (!equalsSameHashCode) {
            description.appendText("to have the same hash code as ")
                .appendValue(same);
        } else {
            description.appendText("to have a different hash code from ")
                .appendValue(different);
        }
    }

    public void describeTo(Description description) {
        if (!equalsSame || !equalsSameHashCode) {
            description.appendValue(same);
        } else {
            description.appendValue(different);
        }
    }

    private final T same;
    private final T different;
    private boolean equalsSame = false;
    private boolean notEqualsDifferent = false;
    private boolean equalsSameHashCode = false;
    private boolean notEqualsDifferentHashCode = false;
}
