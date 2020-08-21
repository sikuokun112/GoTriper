package com.gogotriper.gotriper.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gogotriper.gotriper.entity.*;

import java.util.List;

public class ResonseUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode returnAccount(Account account){
        ObjectNode node = mapper.createObjectNode();
        node.put("userid",account.getUserId());
        node.put("username",account.getUserName());
        node.put("fullname",account.getFullName());
        node.put("email",account.getEmail());
        node.put("enable",account.isEnable());
        return  node;
    }

    public static ArrayNode returnListAccount(List<Account> accounts){
        ArrayNode nodes = mapper.createArrayNode();
        for(Account account:accounts){
            nodes.add(returnAccount(account));
        }
        return nodes;
    }

    public static ObjectNode returnTinhThanh(TinhThanh tinhThanh){
        ObjectNode node = mapper.createObjectNode();
        node.put("tinhthanhid",tinhThanh.getId());
        node.put("tinhthanhname",tinhThanh.getTen());
        node.put("sodiadiem",tinhThanh.getListDiaDiems().size());
        return node;
    }
    public static ArrayNode returnListTinhThanh(List<TinhThanh> listTinhThanhs){
        ArrayNode nodes = mapper.createArrayNode();
        for(TinhThanh t:listTinhThanhs){
            nodes.add(returnTinhThanh(t));
        }
        return nodes;
    }

    public static ObjectNode returnDanhMuc(DanhMuc danhMuc){
        ObjectNode node = mapper.createObjectNode();
        node.put("danhmucid",danhMuc.getId());
        node.put("danhmucname",danhMuc.getTenDanhMuc());
        node.put("sobaidang",danhMuc.getListBaiDang().size());
        return node;
    }
    public static ArrayNode returnListDanhMuc(List<DanhMuc> listDanhMucs){
        ArrayNode nodes = mapper.createArrayNode();
        for(DanhMuc d:listDanhMucs){
            nodes.add(returnDanhMuc(d));
        }
        return nodes;
    }

    public static ObjectNode returnDiaDiem(DiaDiem diaDiem){
        ObjectNode node = mapper.createObjectNode();
        node.put("diadiemid",diaDiem.getId());
        node.put("diadiemname",diaDiem.getTenDiaDiem());
        node.put("tinhthanhid",diaDiem.getTinhThanh().getId());
        node.put("usercreateid",diaDiem.getUserId().getUserId());
        node.put("sobaidang",diaDiem.getListBaiDang_DiaDiem().size());
        return node;
    }
    public static ArrayNode returnListDiaDiem(List<DiaDiem> listDiaDiems){
        ArrayNode nodes = mapper.createArrayNode();
        for(DiaDiem d:listDiaDiems){
            nodes.add(returnDiaDiem(d));
        }
        return nodes;
    }

    public static ObjectNode returnBaiDang(BaiDang baiDang){
        ObjectNode node = mapper.createObjectNode();
        node.put("baidangid",baiDang.getId());
        node.put("baidangname",baiDang.getTieuDe());
        node.put("danhmucid",baiDang.getDanhMuc().getId());
        node.put("usercreateid",baiDang.getUserId().getUserId());
        node.put("diadiemid",baiDang.getDiaDiem().getId());
        node.put("soluotxem",baiDang.getSoLuotXem());
        node.put("soluotdanhgia",baiDang.getListBinhLuans().size());
        node.put("tinhtrang",baiDang.getFlag());
        return node;
    }
    public static ArrayNode returnListBaiDang(List<BaiDang> listBaiDangs){
        ArrayNode nodes = mapper.createArrayNode();
        for(BaiDang bd:listBaiDangs){
            nodes.add(returnBaiDang(bd));
        }
        return nodes;
    }

    public static ObjectNode returnVanDe(VanDe vanDe){
        ObjectNode node = mapper.createObjectNode();
        node.put("vandeid",vanDe.getId());
        node.put("vandename",vanDe.getTieude());
        node.put("noidung",vanDe.getNoidung());
        node.put("usercreateid",vanDe.getUserIdVanDe().getUserId());
        node.put("tinhtrang",vanDe.getFlag());
        return node;
    }
    public static ArrayNode returnListVanDe(List<VanDe> listVanDes){
        ArrayNode nodes = mapper.createArrayNode();
        for(VanDe vanDe:listVanDes){
            nodes.add(returnVanDe(vanDe));
        }
        return nodes;
    }





}
