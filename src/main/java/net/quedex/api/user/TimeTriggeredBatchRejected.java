package net.quedex.api.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class TimeTriggeredBatchRejected {

    public enum Cause {
        TOO_MANY_ACTIVE_TIMERS,
        TIMER_ALREADY_EXPIRED,
        TIMER_ALREADY_EXISTS;

        @JsonCreator
        private static Cause deserialize(String value) {
            return valueOf(value.toUpperCase());
        }
    }

    private final long batchId;
    private final Cause cause;

    @JsonCreator
    public TimeTriggeredBatchRejected(final @JsonProperty("timer_id") long batchId,
                                      final @JsonProperty("cause") Cause cause) {
        this.batchId = batchId;
        this.cause = checkNotNull(cause, "Null cause");
    }

    public long getBatchId() {
        return batchId;
    }

    public Cause getCause() {
        return cause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTriggeredBatchRejected that = (TimeTriggeredBatchRejected) o;
        return batchId == that.batchId &&
            cause == that.cause;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(batchId, cause);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("batchId", batchId)
            .add("cause", cause)
            .toString();
    }
}
