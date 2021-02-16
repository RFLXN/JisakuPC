package resources.csv;

import bean.DBConnectionInfo;
import db.connector.DBCloser;
import db.connector.MySQLConnector;
import db.dao.factory.MySQLDaoFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CsvDbInserter {
    public List<CsvProduct> getCPUs() throws IOException {
        String data = readFile("src/resources/csv/cpu.csv");
        String[] products = data.split("\n");

        ArrayList<CsvProduct> cpuList = new ArrayList<>();

        for (String product : products) {
            String[] buffer = product.split(",");
            String productName = buffer[0];
            String rawProductSpec = buffer[1];

            buffer = rawProductSpec.split(" \\| ");

            String core = null;
            String thread = null;
            String frequency = null;
            String socket = null;
            String date = null;
            String tdp = null;

            for (String spec : buffer) {
                if (spec.contains("コア")) {
                    core = spec.replace(" コア", "");
                } else if (spec.contains("スレッド")) {
                    thread = spec.replace(" スレッド", "");
                } else if (spec.contains("GHz")) {
                    frequency = spec.replace(" GHz", "");
                } else if (spec.contains("W")) {
                    tdp = spec.replace(" W", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "");
                } else {
                    socket = spec;
                }
            }

            JSONObject object = new JSONObject();
            if (core != null) {
                object.put("core", Integer.parseInt(core));
            }
            if (thread != null) {
                object.put("thread", Integer.parseInt(thread));
            }
            if (frequency != null) {
                object.put("frequency", Double.parseDouble(frequency));
            }
            if (socket != null) {
                object.put("socket", socket);
            }
            if (date != null) {
                object.put("date", date);
            }
            if (tdp != null) {
                object.put("tdp", Integer.parseInt(tdp));
            }

            cpuList.add(new CsvProduct(productName, object.toString(), productName.split(" ")[0]));
        }

        return cpuList;
    }

    public List<CsvProduct> getRAMs() throws IOException {
        String data = readFile("src/resources/csv/memory.csv");
        String[] products = data.split("\n");

        ArrayList<CsvProduct> ramList = new ArrayList<>();

        for (String product : products) {
            String[] buff = product.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String clock = null;
            String boostClock = null;
            String ddrType = null;
            String dimmType = null;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("S.O.DIMM")) {
                    dimmType = "S.O.DIMM";
                } else if (spec.contains("DIMM")) {
                    dimmType = "DIMM";
                }

                if (spec.contains("DDR")) {
                    if (spec.contains("(")) {
                        String[] b = spec.split("\\(");

                        String[] b2 = b[0].split("-");
                        ddrType = b2[0];
                        clock = b2[1];

                        b2 = b[1].split("-");
                        boostClock = b2[1].replace(")", "");
                    } else {
                        String[] b = spec.split("-");
                        ddrType = b[0];
                        clock = b[1];
                    }
                }

                if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                }
            }

            CsvProduct ram = new CsvProduct();

            JSONObject object = new JSONObject();

            ram.setProductName(productName);
            ram.setBrand(productName.split(" ")[0]);

            if (clock != null) {
                object.put("clock", Integer.parseInt(clock));
            }

            if (boostClock != null) {
                object.put("boostClock", Integer.parseInt(boostClock));
            }

            if (date != null) {
                object.put("date", date);
            }

            if (ddrType != null) {
                object.put("ddr", ddrType);
            }

            if (dimmType != null) {
                object.put("dimm", dimmType);
            }

            ram.setSpec(object.toString());

            ramList.add(ram);
        }

        return ramList;
    }

    public List<CsvProduct> getHDDs() throws IOException {
        String rawData = readFile("src/resources/csv/hdd.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String size = null;
            String volume = null;
            String speed = null;
            String serial = null;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("容量")) {
                    volume = spec.replace("容量 ", "");
                } else if (spec.contains("rpm")) {
                    speed = spec.replace(" rpm", "");
                } else if (spec.contains("インチ")) {
                    size = spec.replace("インチ", "");
                } else if (spec.contains("Serial")) {
                    serial = spec.replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売\r", "");
                }
            }

            JSONObject object = new JSONObject();

            if (volume != null) {
                object.put("volume", volume);
            }

            if (size != null) {
                object.put("size", size);
            }

            if (speed != null) {
                object.put("speed", Integer.parseInt(speed));
            }

            if (serial != null) {
                object.put("serial", serial);
            }

            if (date != null) {
                object.put("date", date);
            }

            CsvProduct p = new CsvProduct();
            p.setProductName(productName);
            p.setSpec(object.toString());
            p.setBrand(productName.split(" ")[0]);

            list.add(p);
        }

        return list;
    }

    public List<CsvProduct> getSSDs() throws IOException {
        String rawData = readFile("src/resources/csv/ssd.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String size = null;
            String volume = null;
            String flashMemoryType = null;
            String serial = null;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("容量")) {
                    volume = spec.replace("容量 ", "");
                } else if (spec.contains("MLC") | spec.contains("TLC") | spec.contains("QLC")) {
                    flashMemoryType = spec;
                } else if (spec.contains("インチ")) {
                    size = spec.replace("インチ", "");
                } else if (spec.contains("M.2")) {
                    size = spec;
                } else if (spec.contains("Serial") | spec.contains("Express")) {
                    serial = spec.replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売\r", "");
                }
            }

            JSONObject object = new JSONObject();

            if (volume != null) {
                object.put("volume", volume);
            }

            if (size != null) {
                object.put("size", size);
            }

            if (flashMemoryType != null) {
                object.put("flashMemoryType", flashMemoryType);
            }

            if (serial != null) {
                object.put("serial", serial);
            }

            if (date != null) {
                object.put("date", date);
            }

            CsvProduct p = new CsvProduct();
            p.setProductName(productName);
            p.setSpec(object.toString());
            p.setBrand(productName.split(" ")[0]);

            list.add(p);
        }

        return list;
    }

    public List<CsvProduct> getGPUs() throws IOException {
        String rawData = readFile("src/resources/csv/gpu.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String gpuName = null;
            String date = null;
            String memoryType = null;
            String memorySize = null;

            for (String spec : buff) {
                if (spec.contains("GB")) {
                    String[] b = spec.split(" ");
                    for (String b2 : b) {
                        if (b2.contains("GDDR")) {
                            memoryType = b2.replace("\r", "");
                        } else if (b2.contains("GB")) {
                            memorySize = b2.replace("GB", "").replace("\r", "");
                        }
                    }
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売\r", "");
                } else {
                    gpuName = spec.replace("\r", "");
                }
            }

            JSONObject object = new JSONObject();

            if (gpuName != null) {
                object.put("gpu", gpuName);
            }


            if (memorySize != null) {
                object.put("memorySize", Integer.parseInt(memorySize));
            }


            if (memoryType != null) {
                object.put("memoryType", memoryType);
            }

            if (date != null) {
                object.put("date", date);
            }

            CsvProduct p = new CsvProduct();
            p.setProductName(productName);
            p.setSpec(object.toString());
            p.setBrand(productName.split(" ")[0]);

            list.add(p);
        }

        return list;
    }

    public List<CsvProduct> getCases() throws IOException {
        String rawData = readFile("src/resources/csv/case.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String factor = null;
            String[] color = null;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("ATX") | spec.contains("ITX") | spec.contains("STX")
                        | spec.contains("SSI") | spec.contains("DTX")) {
                    factor = spec.replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                } else {
                    color = spec.split("/");
                }
            }

            CsvProduct csvProduct = new CsvProduct();
            csvProduct.setProductName(productName);

            String bname = productName.split(" ")[0];
            if (bname.equals("Lian")) {
                bname = "Lian Li";
            } else if (bname.equals("Fractal")) {
                bname = "Fractal Design";
            } else if (bname.equals("In")) {
                bname = "In Win";
            } else if (bname.equals("Be")) {
                bname = "Be Quiet";
            } else if (bname.equals("Super")) {
                bname = "Super Flower";
            } else if (bname.equals("Dan")) {
                bname = "Dan Cases";
            } else if (bname.equals("EK")) {
                bname = "EK Water Blocks";
            }
            csvProduct.setBrand(bname);

            JSONObject object = new JSONObject();

            if (factor != null) {
                object.put("factor", factor);
            }
            if (color != null && color.length > 0) {
                JSONArray array = new JSONArray();
                for (String c : color) {
                    array.put(c.replace("\r", ""));
                }

                object.put("color", array);
            }
            if (date != null) {
                object.put("date", date);
            }

            csvProduct.setSpec(object.toString());

            list.add(csvProduct);
        }

        return list;
    }

    public List<CsvProduct> getPowers() throws IOException {
        String rawData = readFile("src/resources/csv/power.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String w = null;
            String[] factor = null;
            String plus80 = null;
            boolean plugin = false;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("ATX") | spec.contains("BTX") | spec.contains("EPS") |
                        spec.contains("SFX") | spec.contains("TFX")) {
                    factor = spec.split("/");
                } else if (spec.contains("80PLUS")) {
                    plus80 = spec.replace("80PLUS ", "").replace("\r", "");
                } else if (spec.contains("W")) {
                    w = spec.replace(" W", "").replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                } else if (spec.contains("プラグイン対応")) {
                    plugin = true;
                }
            }

            JSONObject object = new JSONObject();

            if (factor != null && factor.length > 0) {
                JSONArray array = new JSONArray();
                for (String f : factor) {
                    array.put(f.replace("\r", ""));
                }
                object.put("factor", array);
            }

            if (w != null) {
                object.put("W", Integer.parseInt(w));
            }

            if (plus80 != null) {
                object.put("80PLUS", plus80);
            }

            object.put("plugin", plugin);

            if (date != null) {
                object.put("date", date);
            }

            String bname = productName.split(" ")[0];
            if (bname.equals("Lian")) {
                bname = "Lian Li";
            } else if (bname.equals("Fractal")) {
                bname = "Fractal Design";
            } else if (bname.equals("In")) {
                bname = "In Win";
            } else if (bname.equals("Be")) {
                bname = "Be Quiet";
            } else if (bname.equals("Super")) {
                bname = "Super Flower";
            } else if (bname.equals("Dan")) {
                bname = "Dan Cases";
            } else if (bname.equals("EK")) {
                bname = "EK Water Blocks";
            }

            list.add(new CsvProduct(productName, object.toString(), bname));
        }

        return list;
    }

    public List<CsvProduct> getMotherBoards() throws IOException {
        String rawData = readFile("src/resources/csv/board.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String formfactor = null;
            String chipset = null;
            String socket = null;
            String date = null;
            boolean usbTypeC = false;
            boolean wifi = false;
            boolean thunderbolt = false;

            for (String spec : buff) {
                if (spec.contains("ATX") | spec.contains("CEB") | spec.contains("Extended") | spec.contains("ITX") |
                        spec.contains("STX") | spec.contains("Proprietary") | spec.contains("SSI")) {
                    formfactor = spec.replace("\r", "");
                } else if (spec.contains("AMD") | spec.contains("INTEL")) {
                    chipset = spec.replace("\r", "");
                } else if (spec.contains("Socket") | spec.contains("LGA")) {
                    socket = spec.replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                } else if (spec.contains("USB Type-C")) {
                    usbTypeC = true;
                } else if (spec.contains("Thunderbolt 3")) {
                    thunderbolt = true;
                } else if (spec.contains("Wi-Fi")) {
                    wifi = true;
                }
            }

            JSONObject object = new JSONObject();
            if (formfactor != null) {
                object.put("formfactor", formfactor);
            }
            if (chipset != null) {
                object.put("chipset", chipset);
            }
            if (socket != null) {
                object.put("socket", socket);
            }
            if (date != null) {
                object.put("date", date);
            }
            object.put("wifi", wifi);
            object.put("usbTypeC", usbTypeC);
            object.put("thunderbolt", thunderbolt);

            list.add(new CsvProduct(productName, object.toString(), productName.split(" ")[0].replace("\r", "")));
        }

        return list;
    }

    public List<CsvProduct> getCPUCoolers() throws IOException {
        String rawData = readFile("src/resources/csv/cpucooler.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String type = null;
            String size = null;
            String date = null;
            String airVolume = null;

            for (String spec : buff) {
                if (spec.contains("mm")) {
                    size = spec.replace("\r", "");
                } else if (spec.contains("CFM")) {
                    airVolume = spec.replace(" CFM", "").replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                } else if (spec.contains("型") | spec.contains("ファンレス")) {
                    type = spec.replace("\r", "");
                }
            }

            JSONObject object = new JSONObject();
            if (size != null) {
                object.put("size", size);
            }
            if (airVolume != null) {
                object.put("airVolume", Double.parseDouble(airVolume));
            }
            if (date != null) {
                object.put("date", date);
            }
            if (type != null) {
                object.put("type", type);
            }

            String bname = productName.split(" ")[0];

            if (bname.equals("Fractal")) {
                bname = "Fractal Design";
            } else if (bname.equals("Be")) {
                bname = "Be Quiet";
            } else if (bname.equals("In")) {
                bname = "In Win";
            } else if (bname.equals("Lian")) {
                bname = "Lian Li";
            } else if (bname.equals("GELID")) {
                bname = "GELID Solutions";
            } else if (bname.equals("Super")) {
                bname = "Super Flower";
            }

            list.add(new CsvProduct(productName, object.toString(), bname));
        }

        return list;
    }

    public List<CsvProduct> getCaseFans() throws IOException {
        String rawData = readFile("src/resources/csv/casefan.csv");
        String[] products = rawData.split("\n");

        ArrayList<CsvProduct> list = new ArrayList<>();

        for (String rawProduct : products) {
            String[] buff = rawProduct.split(",");
            String productName = buff[0];

            if (buff.length < 2) {
                buff = new String[]{""};
            } else {
                buff = buff[1].split(" \\| ");
            }

            String size = null;
            String rpm = null;
            String sound = null;
            String date = null;

            for (String spec : buff) {
                if (spec.contains("mm")) {
                    size = spec.replace(" mm", "").replace("\r", "");
                } else if (spec.contains("rpm")) {
                    rpm = spec.replace(" rpm", "").replace("\r", "");
                } else if (spec.contains("dB")) {
                    sound = spec.replace(" dB", "").replace("\r", "");
                } else if (spec.contains("発売")) {
                    date = spec.replace("発売", "").replace("\r", "");
                }
            }

            JSONObject object = new JSONObject();

            if (size != null) {
                object.put("size", Integer.parseInt(size));
            }
            if (rpm != null) {
                object.put("rpm", Integer.parseInt(rpm));
            }
            if (sound != null) {
                object.put("noiseLevel", Double.parseDouble(sound));
            }
            if (date != null) {
                object.put("date", date);
            }

            String bname = productName.split(" ")[0];

            if (bname.equals("Fractal")) {
                bname = "Fractal Design";
            } else if (bname.equals("In")) {
                bname = "In Win";
            } else if (bname.equals("Be")) {
                bname = "Be Quiet";
            } else if (bname.equals("Lian")) {
                bname = "Lian Li";
            } else if (bname.equals("CROWN")) {
                bname = "CROWN precision & electronics";
            } else if (bname.equals("Tranyoung")) {
                bname = "Tranyoung Technology";
            } else if (bname.equals("GELID")) {
                bname = "GELID Solutions";
            } else if (bname.equals("BLACKNOISE")) {
                bname = "BLACKNOISE technology";
            }

            list.add(new CsvProduct(productName, object.toString(), bname));
        }

        return list;
    }

    public void insert(List<CsvProduct> productList, String productType) throws Exception {
        DBConnectionInfo info
                = ((MySQLDaoFactory) MySQLDaoFactory.getInstance()).getConnectionInfo();
        Connection connection
                = MySQLConnector.getInstance().getConnection(info.getHost(),
                info.getUserName(), info.getPassword());

        String sql = "INSERT INTO product_table(product_name, product_spec, product_brand, product_type, product_price) "
                + "VALUES (?, ?, ?, ?, 0)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(4, productType);

        for (CsvProduct product : productList) {
            System.out.println("INSERT : " + product.getProductName() + " / " + product.getSpec());
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getSpec());
            statement.setString(3, product.getBrand());

            statement.executeUpdate();
        }

        connection.commit();
        DBCloser.close(connection);
    }

    public void insertAll() throws Exception {
        List<CsvProduct> products = getCPUs();
        insert(products, "cpu");
        products = getGPUs();
        insert(products, "gpu");
        products = getMotherBoards();
        insert(products, "mother_board");
        products = getRAMs();
        insert(products, "ram");
        products = getHDDs();
        insert(products, "storage");
        products = getSSDs();
        insert(products, "storage");
        products = getCases();
        insert(products, "case");
        products = getPowers();
        insert(products, "power_supply");
        products = getCPUCoolers();
        insert(products, "cpu_cooler");
        products = getCaseFans();
        insert(products, "case_fan");
    }

    private String readFile(String filePath) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
        byte[] buffer = new byte[inputStream.available()];

        StringBuilder builder = new StringBuilder();

        while (inputStream.read(buffer) != -1) {
            String str = new String(buffer);
            builder.append(str);
        }

        return builder.toString();
    }
}
