/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sun.library.http.lifecycle.android;

import android.view.View;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.OutsideLifecycleException;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.trello.rxlifecycle3.RxLifecycle.bind;
import static com.trello.rxlifecycle3.internal.Preconditions.checkNotNull;

public class RxLifecycleAndroid {

    private RxLifecycleAndroid() {
        throw new AssertionError("No instances");
    }

    /**
     * Binds the given source to an Activity lifecycle.
     * <p>
     * This helper automatically determines (based on the lifecycle sequence itself) when the source
     * should stop emitting items. In the case that the lifecycle sequence is in the
     * creation phase (CREATE, START, etc) it will choose the equivalent destructive phase (DESTROY,
     * STOP, etc). If used in the destructive phase, the notifications will cease at the next event;
     * for example, if used in PAUSE, it will unsubscribe in STOP.
     * <p>
     * Due to the differences between the Activity and Fragment lifecycles, this method should only
     * be used for an Activity lifecycle.
     *
     * @param lifecycle the lifecycle sequence of an Activity
     * @return a reusable {@link LifecycleTransformer} that unsubscribes the source during the Activity lifecycle
     */
    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindActivity(@NonNull final Observable<ActivityEvent> lifecycle) {
        return bind(lifecycle, ACTIVITY_LIFECYCLE);
    }

    /**
     * Binds the given source to a Fragment lifecycle.
     * <p>
     * This helper automatically determines (based on the lifecycle sequence itself) when the source
     * should stop emitting items. In the case that the lifecycle sequence is in the
     * creation phase (CREATE, START, etc) it will choose the equivalent destructive phase (DESTROY,
     * STOP, etc). If used in the destructive phase, the notifications will cease at the next event;
     * for example, if used in PAUSE, it will unsubscribe in STOP.
     * <p>
     * Due to the differences between the Activity and Fragment lifecycles, this method should only
     * be used for a Fragment lifecycle.
     *
     * @param lifecycle the lifecycle sequence of a Fragment
     * @return a reusable {@link LifecycleTransformer} that unsubscribes the source during the Fragment lifecycle
     */
    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindFragment(@NonNull final Observable<FragmentEvent> lifecycle) {
        return bind(lifecycle, FRAGMENT_LIFECYCLE);
    }

    /**
     * Binds the given source to a View lifecycle.
     * <p>
     * Specifically, when the View detaches from the window, the sequence will be completed.
     * <p>
     * Warning: you should make sure to use the returned Transformer on the main thread,
     * since we're binding to a View (which only allows binding on the main thread).
     *
     * @param view the view to bind the source sequence to
     * @return a reusable {@link LifecycleTransformer} that unsubscribes the source during the View lifecycle
     */
    @NonNull
    @CheckResult
    public static <T> LifecycleTransformer<T> bindView(@NonNull final View view) {
        checkNotNull(view, "view == null");

        return bind(Observable.create(new ViewDetachesOnSubscribe(view)));
    }

    // Figures out which corresponding next lifecycle event in which to unsubscribe, for Activities
    private static final Function<ActivityEvent, ActivityEvent> ACTIVITY_LIFECYCLE =
        new Function<ActivityEvent, ActivityEvent>() {
            @Override
            public com.trello.rxlifecycle3.android.ActivityEvent apply(com.trello.rxlifecycle3.android.ActivityEvent lastEvent) throws Exception {
                switch (lastEvent) {
                    case CREATE:
                        return com.trello.rxlifecycle3.android.ActivityEvent.DESTROY;
                    case START:
                        return com.trello.rxlifecycle3.android.ActivityEvent.STOP;
                    case RESUME:
                        return com.trello.rxlifecycle3.android.ActivityEvent.PAUSE;
                    case PAUSE:
                        return com.trello.rxlifecycle3.android.ActivityEvent.STOP;
                    case STOP:
                        return ActivityEvent.DESTROY;
                    case DESTROY:
                        throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                    default:
                        throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
                }
            }
        };

    // Figures out which corresponding next lifecycle event in which to unsubscribe, for Fragments
    private static final Function<FragmentEvent, FragmentEvent> FRAGMENT_LIFECYCLE =
        new Function<FragmentEvent, FragmentEvent>() {
            @Override
            public com.trello.rxlifecycle3.android.FragmentEvent apply(com.trello.rxlifecycle3.android.FragmentEvent lastEvent) throws Exception {
                switch (lastEvent) {
                    case ATTACH:
                        return com.trello.rxlifecycle3.android.FragmentEvent.DETACH;
                    case CREATE:
                        return com.trello.rxlifecycle3.android.FragmentEvent.DESTROY;
                    case CREATE_VIEW:
                        return com.trello.rxlifecycle3.android.FragmentEvent.DESTROY_VIEW;
                    case START:
                        return com.trello.rxlifecycle3.android.FragmentEvent.STOP;
                    case RESUME:
                        return com.trello.rxlifecycle3.android.FragmentEvent.PAUSE;
                    case PAUSE:
                        return com.trello.rxlifecycle3.android.FragmentEvent.STOP;
                    case STOP:
                        return com.trello.rxlifecycle3.android.FragmentEvent.DESTROY_VIEW;
                    case DESTROY_VIEW:
                        return com.trello.rxlifecycle3.android.FragmentEvent.DESTROY;
                    case DESTROY:
                        return FragmentEvent.DETACH;
                    case DETACH:
                        throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                    default:
                        throw new UnsupportedOperationException("Binding to " + lastEvent + " not yet implemented");
                }
            }
        };

}
