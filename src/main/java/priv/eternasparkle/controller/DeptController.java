package priv.eternasparkle.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import priv.eternasparkle.entity.Dept;
import priv.eternasparkle.entity.User;
import priv.eternasparkle.service.DeptService;
import priv.eternasparkle.service.UserService;
import priv.eternasparkle.util.R;
import priv.eternasparkle.vo.CLeaderVO;
import priv.eternasparkle.vo.DeptListVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门控制器
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@RestController
@RequestMapping("/System/Dept")
public class DeptController {
    /**
     * 部门服务
     */
    private final DeptService deptService;
    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 部门控制器
     *
     * @param deptService 部门服务
     * @param userService 用户服务
     */
    public DeptController(DeptService deptService, UserService userService) {
        this.deptService = deptService;
        this.userService = userService;
    }

    /**
     * 列表部门
     *
     * @return {@link R }
     */
    @GetMapping("/listDept")
    public R listDept() {
        List<Dept> depts = deptService.list();
        List<User> users = userService.list();

        Map<Integer, List<User>> deptUserMap = users.stream()
                .filter(user -> user.getDeptId() != null)
                .collect(Collectors.groupingBy(User::getDeptId));

        Map<Integer, String> leaderNameMap = users.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));

        List<Map<String, Object>> tree = buildDeptTree(depts, "0", deptUserMap, leaderNameMap);
        return R.ok(tree);
    }

    /**
     * 构建部门树
     *
     * @param depts         部门
     * @param parentId
     * @param deptUserMap   部门用户地图
     * @param leaderNameMap Leader Name 映射
     * @return {@link List }<{@link DeptListVO }>
     */
    private List<Map<String, Object>> buildDeptTree(List<Dept> depts, String parentId, Map<Integer, List<User>> deptUserMap, Map<Integer, String> leaderNameMap) {
        return depts.stream()
                .filter(dept -> dept.getParentId().equals(parentId))
                .map(dept -> {
                    Map<String, Object> deptMap = new HashMap<>();
                    deptMap.put("id", dept.getId());
                    deptMap.put("deptName", dept.getDeptName());
                    deptMap.put("leaderId", dept.getLeaderId());
                    String leaderName = leaderNameMap.getOrDefault(dept.getLeaderId(), "Unknown");
                    deptMap.put("leaderName", leaderName);
                    Integer deptId = Integer.parseInt(dept.getId());
                    List<User> deptUsers = deptUserMap.getOrDefault(deptId, List.of());
                    deptMap.put("deptUserCount", deptUsers.size());

                    List<Map<String, Object>> children = buildDeptTree(depts, dept.getId(), deptUserMap, leaderNameMap);
                    if (!children.isEmpty()) {
                        deptMap.put("hasChildren", true);
                        deptMap.put("children", children);
                    }

                    return deptMap;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/getCLeader/{deptId}")
    public R getCLeaderByDeptId(@PathVariable("deptId")String deptId, @RequestParam(value = "word", required = false) String word){
        if (word == null) {
            word = "";
        }
        List<CLeaderVO> leaders = userService.getCLeaderByDeptId(deptId,word);
        return R.ok(leaders);
    }

    @PostMapping("/addDept")
    public R addDept(@RequestBody Dept dept){
        return deptService.save(dept) ? R.ok() : R.err(new Exception("添加失败"));
    }
}
