package teddy.wagproject.model;

import android.support.annotation.NonNull;

/**
 * Created by teddy on 4/8/18.
 * Model for each item in the JSON response
 */

public class StackExchangeItem {
    public String userName;
    public String userId;
    public String bronzeCount;
    public String silverCount;
    public String goldCount;
    public String gravatar;
    public String stackLink;

    private StackExchangeItem(
            String userName,
            String userId,
            String bronzeCount,
            String silverCount,
            String goldCount,
            String gravatar,
            String stackLink) {
        this.userName = userName;
        this.userId = userId;
        this.bronzeCount = bronzeCount;
        this.silverCount = silverCount;
        this.goldCount = goldCount;
        this.gravatar = gravatar;
        this.stackLink = stackLink;
    }

    public static class StackExchangeItemBuilder {
        private String userName;
        private String userId;
        private String bronzeCount;
        private String silverCount;
        private String goldCount;
        private String gravatar;
        private String stackLink;

        public StackExchangeItemBuilder() {
        }

        public StackExchangeItemBuilder userName(@NonNull final String userName) {
            this.userName = userName;
            return this;
        }

        public StackExchangeItemBuilder userId(@NonNull final String userId) {
            this.userId = userId;
            return this;
        }

        public StackExchangeItemBuilder bronzeCount(@NonNull final String bronzeCount) {
            this.bronzeCount = bronzeCount;
            return this;
        }

        public StackExchangeItemBuilder silverCount(@NonNull final String silverCount) {
            this.silverCount = silverCount;
            return this;
        }

        public StackExchangeItemBuilder goldCount(@NonNull final String goldCount) {
            this.goldCount = goldCount;
            return this;
        }

        public StackExchangeItemBuilder gravatar(@NonNull final String gravatar) {
            this.gravatar = gravatar;
            return this;
        }

        public StackExchangeItemBuilder stackLink(final String stackLink) {
            this.stackLink = stackLink;
            return this;
        }

        public StackExchangeItem build() {
            return new StackExchangeItem(
                    userName,
                    userId,
                    bronzeCount,
                    silverCount,
                    goldCount,
                    gravatar,
                    stackLink);
        }
    }
}
