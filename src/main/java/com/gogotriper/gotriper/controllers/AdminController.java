package com.gogotriper.gotriper.controllers;

import com.gogotriper.gotriper.entity.*;
import com.gogotriper.gotriper.repositories.RoleRepository;
import com.gogotriper.gotriper.services.*;
import com.gogotriper.gotriper.utils.ResonseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TinhThanhService tinhThanhService;

    @Autowired
    private BaiDangService baiDangService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private DiaDiemService diaDiemService;

    @Autowired
    private  BinhLuanService binhLuanService;

    @Autowired
    private VanDeService vanDeService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String homeAdmin(Model model){
        List<Account> listaccounts = userService.getAllAccount();
        model.addAttribute("account", ResonseUtils.returnListAccount(listaccounts));
        return "adminlayout/homeadmin";
    }
    @RequestMapping(value = "/editaccount/{id}",method = RequestMethod.POST)
    public String EditAccount(@RequestParam(value = "username") String username,@RequestParam(value = "fullname") String fullname,@RequestParam(value = "email") String email,@RequestParam(value = "enable") Integer enable,@PathVariable String id){
            Account account = userService.findByUserId(Long.parseLong(id));
            account.setUserName(username);
            account.setFullName(fullname);
            if(enable!=null){
                if(enable == 0){
                    account.setEnable(false);
                    Role role = roleRepository.findAllByRoleId(Integer.toUnsignedLong(3));
                    List<Role> roles = new ArrayList<>();
                    roles.add(role);
                    account.setRoles(roles);
                }
                else{
                    account.setEnable(true);
                    Role role = roleRepository.findAllByRoleId(Integer.toUnsignedLong(2));
                    List<Role> roles = new ArrayList<>();
                    roles.add(role);
                    account.setRoles(roles);
                }
            }

            account.setEmail(email);
            userService.saveUser(account);
            return "redirect:/home";

    }

    @RequestMapping(value = "/deleteaccount/{id}",method = RequestMethod.POST)
    public String deleteAccount(@PathVariable String id){
        Account account = userService.findByUserId(Long.parseLong(id));
        account.setEnable(false);
        Role role = roleRepository.findAllByRoleId(Integer.toUnsignedLong(3));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        userService.saveUser(account);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = {"/tinhthanh"},method = RequestMethod.GET)
    public String showTinhThanh(Model model){
        List<TinhThanh> listTinhThanhs = tinhThanhService.getAllTinhThanh();
        model.addAttribute("listTinhThanhs", ResonseUtils.returnListTinhThanh(listTinhThanhs));
        return "adminlayout/tinhthanhadmin";
    }

    @RequestMapping(value = "/edittinhthanh/{id}",method = RequestMethod.POST)
    public String EditTinhThanh(@RequestParam(value = "tinhthanhname") String tinhthanhname,@PathVariable String id){
        TinhThanh tinhThanh = tinhThanhService.getTinhThanhById(Integer.parseInt(id));
        tinhThanh.setTen(tinhthanhname);
        tinhThanhService.saveTinhThanh(tinhThanh);
        return "redirect:/admin/tinhthanh";

    }
    @RequestMapping(value = "/deletetinhthanh/{id}",method = RequestMethod.POST)
    public String deleteTinhThanh(@PathVariable String id){
        TinhThanh tinhThanh = tinhThanhService.getTinhThanhById(Integer.parseInt(id));
        tinhThanhService.removeTinhThanh(tinhThanh);
        return "redirect:/admin/tinhthanh";
    }
    @RequestMapping(value = "/addtinhthanh",method = RequestMethod.POST)
    public String AddTinhThanh(@RequestParam(value = "tentinhthanh") String tinhthanhname){
        TinhThanh tinhThanh = new TinhThanh();
        tinhThanh.setTen(tinhthanhname);
        tinhThanhService.saveTinhThanh(tinhThanh);
        return "redirect:/admin/tinhthanh";

    }

    @RequestMapping(value = {"/danhmuc"},method = RequestMethod.GET)
    public String showDanhMuc(Model model){
        List<DanhMuc> listDanhMucs = danhMucService.getAllDanhMuc();
        model.addAttribute("listDanhMucs", ResonseUtils.returnListDanhMuc(listDanhMucs));
        return "adminlayout/danhmucadmin";
    }

    @RequestMapping(value = "/editdanhmuc/{id}",method = RequestMethod.POST)
    public String EditDanhMuc(@RequestParam(value = "danhmucname") String danhmucname,@PathVariable String id){
        DanhMuc danhMuc = danhMucService.findDanhMucById(Integer.parseInt(id));
        danhMuc.setTenDanhMuc(danhmucname);
        danhMucService.saveDanhMuc(danhMuc);
        return "redirect:/admin/danhmuc";

    }
    @RequestMapping(value = "/deletedanhmuc/{id}",method = RequestMethod.POST)
    public String deleteDanhMuc(@PathVariable String id){
        DanhMuc danhMuc = danhMucService.findDanhMucById(Integer.parseInt(id));
        danhMucService.removeDanhMuc(danhMuc);
        return "redirect:/admin/tinhthanh";
    }
    @RequestMapping(value = "/adddanhmuc",method = RequestMethod.POST)
    public String AddDanhMuc(@RequestParam(value = "tendanhmuc") String tendanhmuc){
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setTenDanhMuc(tendanhmuc);
        danhMucService.saveDanhMuc(danhMuc);
        return "redirect:/admin/danhmuc";

    }

    @RequestMapping(value = {"/diadiem"},method = RequestMethod.GET)
    public String showDiaDiem(Model model){
        List<DiaDiem> listDiaDiems = diaDiemService.findAll();
        model.addAttribute("listDiaDiems", ResonseUtils.returnListDiaDiem(listDiaDiems));
        return "adminlayout/diadiemadmin";
    }

    @RequestMapping(value = "/deletediadiem/{id}",method = RequestMethod.POST)
    public String deleteDiaDiem(@PathVariable String id){
        DiaDiem diaDiem = diaDiemService.findDiaDiemById(Integer.parseInt(id));
        diaDiemService.removeDiaDiem(diaDiem);
        return "redirect:/admin/diadiem";
    }

    @RequestMapping(value = {"/baidang"},method = RequestMethod.GET)
    public String showBaiDang(Model model){
        List<BaiDang> listBaiDangs = baiDangService.getAll();
        model.addAttribute("listBaiDangs", ResonseUtils.returnListBaiDang(listBaiDangs));
        return "adminlayout/baidangadmin";
    }

    @RequestMapping(value = "/editbaidang/{id}",method = RequestMethod.POST)
    public String EditBaiDang(@RequestParam(value = "flag") Integer flag,@PathVariable String id){
        BaiDang baiDang = baiDangService.findBaiDangById(Integer.parseInt(id));
        if(flag != baiDang.getFlag() && flag != 1){
            baiDang.setFlag(0);
            Date now = new Date();
            Timestamp ts = new Timestamp(now.getTime());
            // set ngay dang them 10 ngay
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.DATE,10);
            Date end = c.getTime();
            Timestamp ts2 = new Timestamp(end.getTime());
            baiDang.setThoiGianHetHan(ts2);
        }
        baiDangService.saveBaiDang(baiDang);
        return "redirect:/admin/baidang";

    }

    @RequestMapping(value = "/deletebaidang/{id}",method = RequestMethod.POST)
    public String deleteBaiDang(@PathVariable String id){
        BaiDang baiDang = baiDangService.findBaiDangById(Integer.parseInt(id));
        baiDangService.removeBaiDang(baiDang);
        return "redirect:/admin/baidang";
    }

    @RequestMapping(value = {"/vande"},method = RequestMethod.GET)
    public String showVanDe(Model model){
        List<VanDe> listVanDes = vanDeService.findAll();
        model.addAttribute("listVanDes", ResonseUtils.returnListVanDe(listVanDes));
        return "adminlayout/vandeadmin";
    }

    @RequestMapping(value = "/editvande/{id}",method = RequestMethod.POST)
    public String EditVanDe(@RequestParam(value = "flag") Integer flag,@PathVariable String id){
        VanDe vanDe = vanDeService.findVanDeById(Integer.parseInt(id));
        vanDe.setFlag(flag);
        vanDeService.saveVanDe(vanDe);
        return "redirect:/admin/vande";

    }
}
