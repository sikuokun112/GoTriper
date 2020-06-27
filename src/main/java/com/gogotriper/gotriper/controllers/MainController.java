package com.gogotriper.gotriper.controllers;

import com.gogotriper.gotriper.entity.Account;
import com.gogotriper.gotriper.entity.BaiDang;
import com.gogotriper.gotriper.entity.DanhMuc;
import com.gogotriper.gotriper.entity.Image;
import com.gogotriper.gotriper.services.BaiDangService;
import com.gogotriper.gotriper.services.DanhMucService;
import com.gogotriper.gotriper.services.ImageService;
import com.gogotriper.gotriper.services.UserService;
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

    // test
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {

        return "test";
    }

    // main
    @RequestMapping(value = {"/home"},method =  RequestMethod.GET)
    public String HomeMain(){

        return "layouts/homemain";
    }

    // Do du lieu len thanh header
    @ModelAttribute("danhmuclist")
    public List<DanhMuc> getAllDanhMuc(){
            return danhMucService.getAllDanhMuc();
    }

    @RequestMapping(value = {"/detail"},method =  RequestMethod.GET)
    public String DetailMain(){
        return "layouts/detailmain";
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
}
