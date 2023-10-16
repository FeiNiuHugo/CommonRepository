package com.hugo.trytospeak.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by karonl on 16/4/12.
 */
public class DataJson {

    String json = "{\"_id\":\"570cf77757a46030037d4c30\",\"areas\":" +
            "[{\"name\":\"H@M\",\"area\":[{\"x\":387.1,\"y\":392.1},{\"x\":404.1,\"y\":392.1},{\"x\":406.1,\"y\":462.1},{\"x\":451.1,\"y\":505.1},{\"x\":433.1,\"y\":520.1},{\"x\":486.1,\"y\":574.1},{\"x\":544.1,\"y\":574.1},{\"x\":586.1,\"y\":618.1},{\"x\":610.1,\"y\":606.1},{\"x\":636.1,\"y\":592.1},{\"x\":661.1,\"y\":585.1},{\"x\":685.1,\"y\":572.1},{\"x\":684.1,\"y\":341.1},{\"x\":389.1,\"y\":350.1},{\"x\":387.1,\"y\":389.1}]},{\"name\":\"GAP\",\"area\":[{\"x\":743.1,\"y\":556.1},{\"x\":784.1,\"y\":547.1},{\"x\":825.1,\"y\":549.1},{\"x\":863.1,\"y\":550.1},{\"x\":908.1,\"y\":550.1},{\"x\":938.1,\"y\":554.1},{\"x\":963.1,\"y\":562.1},{\"x\":1002.1,\"y\":569.1},{\"x\":1003.1,\"y\":353.1},{\"x\":810.1,\"y\":357.1},{\"x\":807.1,\"y\":379.1},{\"x\":806.1,\"y\":386.1},{\"x\":809.1,\"y\":413.1},{\"x\":760.1,\"y\":413.1},{\"x\":761.1,\"y\":448.1},{\"x\":744.1,\"y\":448.1},{\"x\":741.1,\"y\":557.1}]},{\"name\":\"G-STAR\",\"area\":[{\"x\":861.1,\"y\":746.1},{\"x\":860.1,\"y\":642.1},{\"x\":808.1,\"y\":639.1},{\"x\":810.1,\"y\":747.1},{\"x\":861.1,\"y\":746.1}]},{\"name\":\"Broadcast\",\"area\":[{\"x\":688.9,\"y\":1331.1},{\"x\":770.9,\"y\":1254.1},{\"x\":777.9,\"y\":1254.1},{\"x\":777.9,\"y\":1482.1},{\"x\":690.9,\"y\":1482.1},{\"x\":687.9,\"y\":1334.1}]},{\"name\":\"icicle\",\"area\":[{\"x\":674.9,\"y\":1176.1},{\"x\":733.9,\"y\":1235.1},{\"x\":792.9,\"y\":1184.1},{\"x\":739.9,\"y\":1121.1},{\"x\":695.9,\"y\":1170.1},{\"x\":687.9,\"y\":1160.1},{\"x\":675.9,\"y\":1177.1}]},{\"name\":\"PullBear\",\"area\":[{\"x\":283.9,\"y\":1420.1},{\"x\":392.9,\"y\":1298.1},{\"x\":389.9,\"y\":1195.1},{\"x\":208.89999999999998,\"y\":1197.1},{\"x\":209.89999999999998,\"y\":1225.1},{\"x\":170.89999999999998,\"y\":1225.1},{\"x\":172.89999999999998,\"y\":1419.1},{\"x\":284.9,\"y\":1422.1}]},{\"name\":\"MOUSSY\",\"area\":[{\"x\":1162.9,\"y\":1137.1},{\"x\":1234.9,\"y\":1134.1},{\"x\":1231.9,\"y\":1033.1},{\"x\":1162.9,\"y\":1029.1},{\"x\":1160.9,\"y\":1138.1}]},{\"name\":\"TRENDIANO\",\"area\":[{\"x\":499.9,\"y\":1265.1},{\"x\":500.9,\"y\":1227.1},{\"x\":552.9,\"y\":1224.1},{\"x\":555.9,\"y\":1122.1},{\"x\":427.9,\"y\":1124.1},{\"x\":431.9,\"y\":1266.1}]},{\"name\":\"Nike\",\"area\":[{\"x\":401.1,\"y\":917.1},{\"x\":401.1,\"y\":885.1},{\"x\":413.1,\"y\":855.1},{\"x\":416.1,\"y\":832.1},{\"x\":423.1,\"y\":822.1},{\"x\":377.1,\"y\":820.1},{\"x\":372.1,\"y\":741.1},{\"x\":191.1,\"y\":743.1},{\"x\":191.1,\"y\":809.1},{\"x\":85.1,\"y\":813.1},{\"x\":83.1,\"y\":918.1}]},{\"name\":\"OYSHO\",\"area\":[{\"x\":1276.9,\"y\":869.1},{\"x\":1347.9,\"y\":867.1},{\"x\":1348.9,\"y\":837.1},{\"x\":1391.9,\"y\":837.1},{\"x\":1391.9,\"y\":830.1},{\"x\":1407.9,\"y\":828.1},{\"x\":1410.9,\"y\":716.1},{\"x\":1295.9,\"y\":712.1},{\"x\":1288.9,\"y\":734.1},{\"x\":1276.9,\"y\":741.1},{\"x\":1275.9,\"y\":869.1}]},{\"name\":\"FILA\",\"area\":[{\"x\":933.9,\"y\":1480.1},{\"x\":965.9,\"y\":1481.1},{\"x\":1058.9,\"y\":1379.1},{\"x\":1004.9,\"y\":1326.1},{\"x\":932.9,\"y\":1405.1},{\"x\":933.9,\"y\":1475.1}]},{\"name\":\"EP\",\"area\":[{\"x\":607.9,\"y\":1776.1},{\"x\":718.9,\"y\":1772.1},{\"x\":725.9,\"y\":1773.1},{\"x\":725.9,\"y\":1688.1},{\"x\":783.9,\"y\":1687.1},{\"x\":781.9,\"y\":1675.1},{\"x\":666.9,\"y\":1674.1},{\"x\":606.9,\"y\":1625.1},{\"x\":606.9,\"y\":1775.1}]" +
            "}]}";
    JSONTokener jsonTokener = new JSONTokener(json);
    JSONArray array;

    public DataJson() {
        try {
            JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
            array = jsonObject.getJSONArray("areas");

        } catch (JSONException e) {

        }
    }

    public int size() {
        return array.length();
    }

    public JSONObject getArray(int i) {
        JSONObject json = null;
        try {
            json = (JSONObject) this.array.get(i);
        } catch (JSONException e) {

        }
        return json;
    }
}
