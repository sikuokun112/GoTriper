package com.gogotriper.gotriper.controllers;

import com.gogotriper.gotriper.entity.*;
import com.gogotriper.gotriper.services.*;
import com.gogotriper.gotriper.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private BaiDangService baiDangService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DiaDiemService diaDiemService;

    @Autowired
    private BinhLuanService binhLuanService;

    @Autowired
    private TinhThanhService tinhThanhService;

    // Do du lieu len thanh header
    @ModelAttribute("danhmuclist")
    public List<DanhMuc> getAllDanhMuc(){
        return danhMucService.getAllDanhMuc();
    }

    @ModelAttribute("tinhthanhlist")
    public List<TinhThanh> getAllTinhThanh(){
        return tinhThanhService.getAllTinhThanh();
    }


    @RequestMapping(value = "/listdanhmuc", method = RequestMethod.GET)
    public String ShowListDanhMuc(Model model) {

        model.addAttribute("danhmuclist",danhMucService.getAllDanhMuc());
        BaiDang baiDang = baiDangService.findBaiDangById(2);
        List<Image> img = imageService.findAllImageByBaiDang(baiDang);
        model.addAttribute("img",img);
        return "test_danhmuc";
    }

    @PostMapping("/savebaidang")
    public String saveBaiDang(@RequestParam("tieude") String tieude , @RequestParam("noidung") String noidung, @RequestParam("danhmuc") String danhmuc, @RequestParam("photo")List<MultipartFile> photos,Principal principal){
        System.out.println(danhMucService.findDanhMucByName(danhmuc).getTenDanhMuc());
        BaiDang baiDang = new BaiDang();
        baiDang.setDanhMuc(danhMucService.findDanhMucByName(danhmuc));
        baiDang.setNoiDung(noidung);
        baiDang.setTieuDe(tieude);
        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        baiDang.setUserId(user);
        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
                Image image = new Image();
                i++;
                int series = imageService.getLatestIdImage()+i;
                image.setImageUrl(series+"_"+photo.getOriginalFilename());
                image.setBaiDangImage(baiDang);
                images.add(image);
                imageService.saveImageToUploads(photo,series);
        }
        baiDang.setListImage_BaiDang(images);
        baiDangService.saveBaiDang(baiDang);
        return "success";
    }

    @PostMapping("/diadiem/{id}/savebaidang2")
    public String saveBaiDang2(@RequestParam("tieude") String tieude , @RequestParam("noidung") String noidung, @RequestParam("danhmucname") String danhmucname, @RequestParam("pro-image")List<MultipartFile> photos,Principal principal,@PathVariable String id){
        BaiDang baiDang = new BaiDang();
        baiDang.setDanhMuc(danhMucService.findDanhMucById(Integer.parseInt(danhmucname)));
        baiDang.setDiaDiem(diaDiemService.findDiaDiemById(Integer.parseInt(id)));
        baiDang.setNoiDung(noidung);
        baiDang.setTieuDe(tieude);
        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        baiDang.setUserId(user);
        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setBaiDangImage(baiDang);
            images.add(image);
            imageService.saveImageToUploads(photo,series);
        }
        baiDang.setListImage_BaiDang(images);
        baiDangService.saveBaiDang(baiDang);
        return "success";
    }

    // test
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {

        return "layouts/userInfor";
    }

    // main
    @RequestMapping(value = {"/home"},method =  RequestMethod.GET)
    public String HomeMain(){

        return "layouts/homemain";
    }

    @RequestMapping(value = "/diadiem/{id}/dangbaiviet", method = RequestMethod.GET)
    public String dangBaiViet(Model model,@PathVariable String id) {

        System.out.println("ID :"+id);
        model.addAttribute("diadiem",diaDiemService.findDiaDiemById(Integer.parseInt(id)));
        return "layouts/dangbaiviet";
    }

    @RequestMapping(value = "/dangdiadiem", method = RequestMethod.GET)
    public String dangDiaDiem(Model model) {

        return "dangdiadiem";
    }



    @PostMapping("/savediadiem")
    public String saveDiaDiem(@RequestParam("tendiadiem") String tendiadiem,@RequestParam("tinhthanhname") String tinhthanhname,@RequestParam("giohoatdong") String giohoatdong,@RequestParam("giodongcua") String giodongcua,@RequestParam("phone") int phone, @RequestParam("giamin") int giamin,@RequestParam("giamax") int giamax,@RequestParam("diachi") String diachi, @RequestParam("kinhdo") String kinhdo,@RequestParam("vido") String vido,@RequestParam("noidung") String noidung, @RequestParam("pro-image") List<MultipartFile> photos,Principal principal){

        System.out.println(photos.get(0).getOriginalFilename());
        DiaDiem diaDiem = new DiaDiem();
        diaDiem.setTenDiaDiem(tendiadiem);
        diaDiem.setGiamin(giamin);
        diaDiem.setGiamax(giamax);
        diaDiem.setGioHoatDong(new Date());
        diaDiem.setGioDongCua(new Date());
        diaDiem.setKinhDo(kinhdo);
        diaDiem.setViDo(vido);
        diaDiem.setDiaChi(diachi);
        diaDiem.setNoiDung(noidung);
        diaDiem.setSdt(phone);
        String userName = principal.getName();
        Account user  = userService.findByUserName(userName);
        diaDiem.setUserId(user);
        List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setDiaDiemImage(diaDiem);
            images.add(image);
            imageService.saveImageToUploads(photo,series);
        }
        TinhThanh tinhThanh = tinhThanhService.getTinhThanhById(Integer.parseInt(tinhthanhname));
        diaDiem.setTinhThanh(tinhThanh);
        diaDiem.setListImage_DiaDiem(images);
        diaDiemService.saveDiaDiem(diaDiem);
        return "success";
    }


    @PostMapping("/savebinhluan")
    public String saveBinhluan(@RequestParam("tieude") String tieude, @RequestParam("diemdanhgia") Float diemdanhgia,@RequestParam("noidung") String noidung, Principal principal){
        // thay the ID bang Pathvariable ID
        int id =1;
         BaiDang baiDang =  baiDangService.findBaiDangById(id);

        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setTieude(tieude);
        binhLuan.setDiemdanhgia(diemdanhgia);
        binhLuan.setNoidung(noidung);

        binhLuan.setUserId(userService.findByUserName(principal.getName()));
        binhLuan.setBaiDang(baiDang);
        binhLuanService.saveBinhLuan(binhLuan);

        return "redirect:chitietbaidang";

    }


    @RequestMapping(value = {"/detail"},method =  RequestMethod.GET)
    public String DetailMain(Model model){
        DiaDiem diaDiem = diaDiemService.findDiaDiemById(1);
        List<Image> img = imageService.findAllImageByDiaDiem(diaDiem);
        model.addAttribute("img",img);
        model.addAttribute("diadiem",diaDiem);
        return "layouts/detailmain";
    }

    @RequestMapping(value = {"/chitietbaidang"},method =  RequestMethod.GET)
    public String ChiTietBaiDang(Model model, Principal principal){
        BaiDang baiDang = baiDangService.findBaiDangById(1);
        List<Image> img = imageService.findAllImageByBaiDang(baiDang);
        Account account = userService.findByUserName(principal.getName());
        model.addAttribute("img",img);
        model.addAttribute("baidang",baiDang);
        model.addAttribute("account",account);
        return "layouts/chitietbaidang";
    }

    @RequestMapping(value = "/mybaidang",method = RequestMethod.GET)
    public String myBaiDang(Model model ,Principal principal){
        Account account = userService.findByUserName(principal.getName());
        model.addAttribute("account",account);
        return "layouts/myBaiDang";
    }

    @RequestMapping(value = {"/listing"},method =  RequestMethod.GET)
    public String Listing(){

        return "layouts/listingmain";
    }
    //main


    @RequestMapping(value = {"/signup"},method =  RequestMethod.GET)
    public String registerForm(Model model) {

        model.addAttribute("user", new Account());
        return "registerForm";
    }

    @RequestMapping(value = {"/signup2"},method =  RequestMethod.GET)
    public String registerForm2(Model model) {

        model.addAttribute("user", new Account());
        return "signup";
    }



    @PostMapping("/register")
    public String registerUser(@Valid  Account account, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registerForm2";
        }
        if(userService.isUserPresent(account.getUserName())) {
            model.addAttribute("exist",true);
            System.out.println("TAI KHOAN DA CO NGƯƠI SU DUNG");
            return "redirect:/signup2";
        }else{
            userService.createUser(account);
            return "success";
        }


    }

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage2(Model model) {

        return "login";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }


    // Profile Page
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String getProFile(Model model,Principal principal){
        String userName = principal.getName();
        Account user = userService.findByUserName(userName);
        boolean check = false;
        if(user.getListUserImages().size() == 0){
            check = false;

        }else{
            check = true;
        }
        model.addAttribute("userInfor",user);
        model.addAttribute("check",check);
        return "layouts/userInfor";
    }

    @PostMapping("/postavatar/{id}/saveavatar")
    public String changeAvatar(Model model, @PathVariable String id ,@RequestParam("pro-image") List<MultipartFile> photos){
            Account account = userService.findByUserId(Long.parseLong(id));
            List<Image > images = new ArrayList<>();
        int i =0;
        for(MultipartFile photo : photos){
            Image image = new Image();
            i++;
            int series = imageService.getLatestIdImage()+i;
            image.setImageUrl(series+"_"+photo.getOriginalFilename());
            image.setUserImage(account);
            images.add(image);

            imageService.saveImageToUploads(photo,series);
        }
        account.setListUserImages(images);
        userService.saveUser(account);


        return "redirect:/profile";
    }

}
