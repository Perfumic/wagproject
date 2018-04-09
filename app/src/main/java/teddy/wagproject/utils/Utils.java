package teddy.wagproject.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import teddy.wagproject.model.StackExchangeItem;

/**
 * Created by teddy on 4/8/18.
 * Utils helper
 */

public class Utils {
    private static final String TAG = "Utils";

    public static String getOfflineFileNameForUserId(@NonNull Context context, @NonNull String
            userId) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + userId + ".png";
    }

    public static List<StackExchangeItem> parseResponseIntoItems(String response) {
        List<StackExchangeItem> parsed = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray items = object.getJSONArray(Constants.JSON_KEY_ITEMS);
            for (int i = 0; i < items.length(); i++) {
                StackExchangeItem stackItem;
                StackExchangeItem.StackExchangeItemBuilder builder = new StackExchangeItem
                        .StackExchangeItemBuilder();
                JSONObject item = items.getJSONObject(i);
                JSONObject badges = item.getJSONObject(Constants.JSON_KEY_BADGE_COUNTS_);
                builder.bronzeCount(badges.getString(Constants.JSON_KEY_BRONZE));
                builder.silverCount(badges.getString(Constants.JSON_KEY_SILVER));
                builder.goldCount(badges.getString(Constants.JSON_KEY_GOLD));
                builder.userId(item.getString(Constants.JSON_KEY_ACCOUNT_ID));
                builder.userName(item.getString(Constants.JSON_KEY_DISPLAY_NAME));
                builder.stackLink(item.getString(Constants.JSON_KEY_LINK));
                builder.gravatar(item.getString(Constants.JSON_KEY_PROFILE_IMAGE));
                stackItem = builder.build();
                parsed.add(stackItem);

            }
        } catch (JSONException e) {
            Log.d(TAG, "JSON parse exception: " + e.getLocalizedMessage());
        }

        return parsed;
    }
}
